package com.stockxsteals.app.viewmodel.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.dto.offerings.QOffering
import com.qonversion.android.sdk.dto.offerings.QOfferings
import com.qonversion.android.sdk.listeners.QonversionOfferingsCallback

class QonversionViewModel: ViewModel() {

  var offerings by mutableStateOf<List<QOffering>>(emptyList())
    private set

  init {
    loadOfferings()
  }

  private fun loadOfferings() {
    Qonversion.shared.offerings(object : QonversionOfferingsCallback {
      override fun onError(error: QonversionError) = Unit

      override fun onSuccess(offerings: QOfferings) {
        this@QonversionViewModel.offerings = offerings.availableOfferings
      }
    })
  }
}