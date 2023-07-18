package com.stockxsteals.app.viewmodel.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QEntitlement
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.dto.offerings.QOffering
import com.qonversion.android.sdk.dto.offerings.QOfferings
import com.qonversion.android.sdk.listeners.QonversionEntitlementsCallback
import com.qonversion.android.sdk.listeners.QonversionOfferingsCallback

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
}