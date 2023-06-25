package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import db.entity.DailySearchQuota
import db.entity.Premium

@Composable
fun TrendCoroutineOnClick(trendsModel: TrendsUIViewModel,
                          networkModel: NetworkViewModel,
                          navController: NavHostController,
                          context: Context,
                          searchQuota: DailySearchQuota,
                          premiumQuota: Premium,
                          displayItem: MutableState<Boolean>
) {


  LaunchedEffect(key1 = true) {
    if (networkModel.checkConnection(context)) {
      if (trendsModel.getSearchModel().dbLogic(searchQuota) == 1 || premiumQuota.isPremium.toInt() == 1) {
        if (premiumQuota.isPremium.toInt() == 0) {
          Toast
            .makeText(
              context,
              "${searchQuota.search_limit - searchQuota.search_number} free daily searches left.",
              Toast.LENGTH_LONG
            )
            .show()
        }
        displayItem.value = true
      } else {
        Toast
          .makeText(context, "Please upgrade to L8test+.", Toast.LENGTH_LONG)
          .show()
      }
      if (displayItem.value)
        navController.navigate(AppScreens.Search.route)


    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun TrendCoroutineDB(displayItem: MutableState<Boolean>,
                     trendsModel: TrendsUIViewModel,
                     productModel: ProductSearchViewModel,
                     trend: Trend,
                     navController: NavHostController,
) {

  LaunchedEffect(key1 = displayItem.value) {
    if (displayItem.value) {
      trendsModel.getHistoryModel()
        .addSearch(
          timestamp = getCurrentDate(),
          country = "US", currency = "USD", sizeType = "US_M",
          size = 0.0,
          name = trend.name,
          image = trend.image,
          ""
        )
      navController.navigate(AppScreens.Search.route)
    }
  }

  if (displayItem.value)
    productModel.addProduct(
      trend.slug,
      "US",
     "USD"
    )
}

