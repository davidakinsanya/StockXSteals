package com.stockxsteals.app.view.compnents.premium

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.QonversionError
import com.qonversion.android.sdk.QonversionPermissionsCallback
import com.qonversion.android.sdk.dto.QPermission
import com.stockxsteals.app.utils.conversionEvent
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun paymentFlow(scope: CoroutineScope,
                settingModel: SettingViewModel,
                context: Context): Int {
  var success = 0

  Qonversion.purchase(context = context as Activity,
    product = settingModel.getQonversionModel().offerings[0].products.firstOrNull()!!,
    callback = object : QonversionPermissionsCallback {
      override fun onError(error: QonversionError) {
       Toast.makeText(context, error.description, Toast.LENGTH_SHORT).show()
      }

      override fun onSuccess(permissions: Map<String, QPermission>) {
        val firebase = FirebaseAnalytics.getInstance(context)
        conversionEvent(firebase)
        Toast.makeText(context, "You have now upgraded to L8test+", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "You now have access to our Discord in the settings " +
                                     "under 'Socials.'", Toast.LENGTH_SHORT).show()
        settingModel.getQonversionModel().updatePermissions()
        scope.launch {
          settingModel.getPremiumModel().setIsPremium(1)
          success = 1
        }
      }
    }

  )
  return success
}