package com.stockxsteals.app.view.compnents.premium

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QEntitlement
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.listeners.QonversionEntitlementsCallback
import com.stockxsteals.app.utils.conversionEvent
import com.stockxsteals.app.utils.getDiscord
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun paymentFlow(scope: CoroutineScope,
                settingModel: SettingViewModel,
                context: Context): Int {
  var success = 0


  Qonversion.shared.purchase(context = context as Activity,
    product = settingModel.getQonversionModel().offerings[0].products.firstOrNull()!!,
    callback = object : QonversionEntitlementsCallback {
      override fun onError(error: QonversionError) {}

      override fun onSuccess(entitlements: Map<String, QEntitlement>) {
        val firebase = FirebaseAnalytics.getInstance(context)
        conversionEvent(firebase)
        Toast.makeText(context, "You have now upgraded to L8test+", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "You now have access to our Discord in the settings " +
                                     "under 'SocialMedia.'", Toast.LENGTH_SHORT).show()
        settingModel.getQonversionModel().updatePermissions()
        scope.launch {
          settingModel.getPremiumModel().setIsPremium(1)
          getDiscord(context)
          success = 1
        }
      }
    }

  )
  return success
}