package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.google.firebase.database.FirebaseDatabase
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QEntitlement
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.dto.offerings.QOffering
import com.qonversion.android.sdk.dto.offerings.QOfferings
import com.qonversion.android.sdk.listeners.QonversionEntitlementsCallback
import com.qonversion.android.sdk.listeners.QonversionOfferingsCallback
import com.stockxsteals.app.BuildConfig.FIREBASE_DB

class QonversionViewModel: ViewModel() {

  var offerings by mutableStateOf<List<QOffering>>(emptyList())
    private set

  var hasPremiumPermission by mutableStateOf(false)
    private set

  init {
    loadOfferings()
    updatePermissions()
  }

  private fun loadOfferings() {
    Qonversion.shared.offerings(object : QonversionOfferingsCallback {
      override fun onError(error: QonversionError) = Unit

      override fun onSuccess(offerings: QOfferings) {
        this@QonversionViewModel.offerings = offerings.availableOfferings
      }
    })
  }

  fun updatePermissions() {
    Qonversion.shared.checkEntitlements(object : QonversionEntitlementsCallback {
      override fun onError(error: QonversionError) = Unit

      override fun onSuccess(entitlements: Map<String, QEntitlement>) {
       hasPremiumPermission = entitlements["4634623343721453467"]?.isActive == true
      }

    })
  }

  fun billingClient(context: Context): Int {
    val db = FirebaseDatabase.getInstance(FIREBASE_DB)
    val rootNode = db.getReference("Token")

    var acknowledgedNum = 0
    var acknowledgement: AcknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder().build()
    var consumeParams = ConsumeParams.newBuilder().build()

    val billingClient = BillingClient.newBuilder(context).setListener { billingResult, purchases ->
      if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
        for (purchase in purchases)
          if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!rootNode.get().result.hasChild(purchase.purchaseToken)) {
              rootNode.setValue(purchase.purchaseToken)
              !purchase.isAcknowledged
              acknowledgement = AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
              consumeParams =
                ConsumeParams.newBuilder()
                  .setPurchaseToken(purchase.purchaseToken)
                  .build()
            }
          }
      }
    }.build()

    if (acknowledgement.purchaseToken.isNotEmpty() && consumeParams.purchaseToken.isNotEmpty()) {
      billingClient.acknowledgePurchase(acknowledgement) { billingResult ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) acknowledgedNum = 1
      }

      billingClient.consumeAsync(consumeParams) { billingResult, _ ->
        acknowledgedNum =
          if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) 1 else 0
      }
    }

    return acknowledgedNum
  }
}