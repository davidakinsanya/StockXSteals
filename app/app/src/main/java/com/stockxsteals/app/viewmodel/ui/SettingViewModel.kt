package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.*
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import db.entity.Premium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel(private val qonversionModel: QonversionViewModel,
                       private val premiumModel: PremiumViewModel,
                       private val historyModel: DailySearchHistoryViewModel): ViewModel() {


  fun getQonversionModel(): QonversionViewModel {
    return qonversionModel
  }

  fun getPremiumModel(): PremiumViewModel {
    return premiumModel
  }

  fun getHistoryModel(): DailySearchHistoryViewModel {
    return historyModel
  }

  suspend fun isPremium(premium: List<Premium>): Boolean {
    withContext(Dispatchers.IO) {
      if (premium.isEmpty()) {
        getPremiumModel().newPremiumQuota()
        return@withContext false
      }
      return@withContext getPremiumModel().getIsPremium(premium[0].id) == 1
    }
    return false
  }

  fun billingClient(context: Context) {
    val billingClient = BillingClient
      .newBuilder(context).setListener { _, _ -> }
      .enablePendingPurchases()
      .build()
    billingClient.startConnection(object : BillingClientStateListener {
      override fun onBillingServiceDisconnected() {
        startConnection(billingClient, context)
      }

      override fun onBillingSetupFinished(p0: BillingResult) {
        if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
          queryPurchaseHistory(billingClient)
        }
      }
    })
  }

  private fun startConnection(billingClient: BillingClient, context: Context) {
    billingClient.startConnection(object : BillingClientStateListener {
      override fun onBillingServiceDisconnected() {
        startConnection(billingClient, context)
      }

      override fun onBillingSetupFinished(p0: BillingResult) {
        if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
          queryPurchaseHistory(billingClient)
        }
      }
    })
  }

  private fun queryPurchaseHistory(billingClient: BillingClient) {

    val queryPurchasesAsync =
      QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build()

    billingClient.queryPurchasesAsync(queryPurchasesAsync) { billingResult, purchases ->
      if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases.isNotEmpty()) {
        for (purchase in purchases) {
          viewModelScope.launch {
            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && purchase.isAutoRenewing) {

              if (purchase.products[0] == "premium_access"
                &&
                getPremiumModel().getIsPremium(1) != 1
              ) {
                getPremiumModel().setIsPremium(1)
              }
            }

            else if (purchase.products[0] == "premium_access"
              && !purchase.isAutoRenewing
              && getPremiumModel().getIsPremium(1) != 0) {

              getPremiumModel().setIsPremium(0)
            }

          }
        }
      }
    }
  }
}