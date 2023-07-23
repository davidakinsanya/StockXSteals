package com.stockxsteals.app.view.compnents.premium

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.dto.QEntitlement
import com.qonversion.android.sdk.dto.QonversionError
import com.qonversion.android.sdk.dto.QonversionErrorCode
import com.qonversion.android.sdk.dto.products.QProduct
import com.qonversion.android.sdk.listeners.QonversionEntitlementsCallback
import com.stockxsteals.app.utils.conversionEvent
import com.stockxsteals.app.utils.getDiscord
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import db.entity.Trends
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun paymentFlow(settingModel: SettingViewModel,
                trendsModel: TrendsUIViewModel,
                navController: NavHostController,
                context: Context,
                scope: CoroutineScope,
                trends: List<Trends>) {

  val firebase = FirebaseAnalytics.getInstance(context)
  var product: QProduct? = null

  settingModel.getQonversionModel().offerings.forEach {
    if (it.offeringID == "l8test_plus") {
      product = it.products.firstOrNull()
    }
  }

  Qonversion.shared.purchase(context = context as Activity,
    product = product!!,
    callback = object : QonversionEntitlementsCallback {
      override fun onError(error: QonversionError) {
        if (error.code == QonversionErrorCode.ProductAlreadyOwned) {
          Toast.makeText(context, "Apologies, Welcome Back To L8test+", Toast.LENGTH_LONG).show()
          scope.launch {
            trendsModel.accessTrends(trends, context)
            navController.navigate("trends_route")
          }
        } else {
          Toast.makeText(context, error.description, Toast.LENGTH_LONG).show()
        }
      }

      override fun onSuccess(entitlements: Map<String, QEntitlement>) {
        conversionEvent(firebase)
        getDiscord(context)

        Toast.makeText(context, "You have now upgraded to L8test+", Toast.LENGTH_SHORT).show()
        scope.launch {
          trendsModel.accessTrends(trends, context)
          navController.navigate("trends_route")
        }
      }
    }
  )
}