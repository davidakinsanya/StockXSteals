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
                          productModel: ProductSearchViewModel,
                          navController: NavHostController,
                          context: Context,
                          trend: Trend,
                          searchQuota: DailySearchQuota,
                          premiumQuota: Premium,
                          displayItem: MutableState<Boolean>
                          ) {


  LaunchedEffect(key1 = true) {
    if (networkModel.checkConnection(context)) {
      if (trendsModel.getSearchModel().dbLogic(searchQuota) == 1 || premiumQuota.isPremium.toInt() == 1) {
        trendsModel.getHistoryModel()
          .addSearch(
            getCurrentDate(),
            country = "TRENDS", currency = "TRENDS", sizeType = "TRENDS",
            size = 0.0,
            trend.image,
            trend.name,
            ""
          )

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
      if (displayItem.value) {
        productModel.addTrend(trend)
        navController.navigate(AppScreens.Search.route)
      }

    } else {
      networkModel.toastMessage(context)
    }
  }
}
