package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.utils.productView
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import db.entity.DailySearchQuota
import db.entity.Premium

@Composable
fun TrendCoroutineOnClick(networkModel: NetworkViewModel,
                          productModel: ProductSearchViewModel,
                          navController: NavHostController,
                          context: Context,
                          searchQuota: DailySearchQuota,
                          premiumQuota: Premium,
                          displayItem: MutableState<Boolean>,
                          trend: Trend,
                          resultBool: Boolean
) {


  LaunchedEffect(key1 = true) {
    if (networkModel.checkConnection(context)) {
      if (productModel.getSearchModel().dbLogic(searchQuota, premiumQuota.isPremium.toInt()) == 1) {
        if (premiumQuota.isPremium.toInt() == 0 && resultBool) {
          val diff = searchQuota.search_limit - searchQuota.search_number
          val toast = if (diff.toInt() == 0)
            "Please upgrade to L8test+."
          else "$diff free daily searches left."
          Toast
            .makeText(
              context,
              toast,
              Toast.LENGTH_LONG
            )
            .show()
        }
        displayItem.value = true
      } else {
        productModel.setCurrentTrends(trend)
        productModel.setDailySearchQuota(searchQuota)
        navController.navigate(AppScreens.Premium.route)
      }
    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun TrendCoroutineDB(displayItem: MutableState<Boolean>,
                     productModel: ProductSearchViewModel,
                     trend: Trend,
                     searchQuota: DailySearchQuota,
                     navController: NavHostController,
                     context: Context

) {
  val firebase = FirebaseAnalytics.getInstance(context)

  LaunchedEffect(key1 = displayItem.value) {
    if (displayItem.value) {
      productModel.getHistoryModel()
        .addSearch(
          timestamp = getCurrentDate(),
          country = "null", currency = "null", sizeType = "null",
          size = 0.0,
          name = trend.name,
          image = trend.image,
          ""
        )
     navController.navigate(AppScreens.Search.route)
    }
  }

  if (displayItem.value) {
    productModel.addProduct(
      trend.slug,
      "",
      "",
      searchQuota,
      navController,
      context
    )
    productView(firebase)
  }
}

