package com.stockxsteals.app.view.compnents.settings

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.QonversionError
import com.qonversion.android.sdk.QonversionPermissionsCallback
import com.qonversion.android.sdk.dto.QPermission
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun paymentFlow(scope: CoroutineScope,
                settingModel: SettingViewModel,
                context: Context) {

  Qonversion.purchase(context = context as Activity,
    product = settingModel.getQonversionModel().offerings[0].products.firstOrNull()!!,
    callback = object : QonversionPermissionsCallback {
      override fun onError(error: QonversionError) {
       Toast.makeText(context, error.description, Toast.LENGTH_LONG).show()
      }

      override fun onSuccess(permissions: Map<String, QPermission>) {
        Toast.makeText(context, "You have now upgraded to L8test+", Toast.LENGTH_LONG).show()
        settingModel.getQonversionModel().updatePermissions()
        scope.launch { settingModel.getPremiumModel().setIsPremium(1) }
      }
    }

  )

}