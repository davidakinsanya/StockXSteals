package com.stockxsteals.app.view.compnents.premium

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QEntitlement
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.listeners.QonversionEntitlementsCallback
import com.stockxsteals.app.utils.conversionErrorEvent
import com.stockxsteals.app.utils.conversionEvent
import com.stockxsteals.app.utils.getDiscord
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun paymentFlow(scope: CoroutineScope,
                settingModel: SettingViewModel,
                context: Context): Int {

  val firebase = FirebaseAnalytics.getInstance(context)
  var success = 0

  Qonversion.shared.purchase(context = context as Activity,
    product = settingModel.getQonversionModel().offerings[0].products.firstOrNull()!!,
    callback = object : QonversionEntitlementsCallback {
      override fun onError(error: QonversionError) {
        conversionErrorEvent(firebase)
      }

      override fun onSuccess(entitlements: Map<String, QEntitlement>) {
        val premiumEntitlement = entitlements["4634623343721453467"]
        if (premiumEntitlement != null && premiumEntitlement.isActive) {

          val client = settingModel.getQonversionModel().billingClient(context)
          Qonversion.shared.syncPurchases()
          conversionEvent(firebase)

          Toast.makeText(context, "You have now upgraded to L8test+", Toast.LENGTH_SHORT).show()
          Toast.makeText(context, "You now have access to our Discord in the settings " +
                  "under 'Social Media.'", Toast.LENGTH_SHORT).show()

          settingModel.getQonversionModel().updatePermissions()
          scope.launch {
            settingModel.getPremiumModel().setIsPremium(1)
            getDiscord(context)
            success = 1
          }
        }
      }
    }
  )
  return success
}