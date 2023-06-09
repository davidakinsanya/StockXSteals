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

@Composable
fun TrendCoroutineOnClick(trendsModel: TrendsUIViewModel,
                          networkModel: NetworkViewModel,
                          productModel: ProductSearchViewModel,
                          navController: NavHostController,
                          context: Context,
                          noQuota: Boolean,
                          trend: Trend,
                          quota: DailySearchQuota?,
                          displayItem: MutableState<Boolean>
                          ) {

  val premiumQuotas = productModel
    .getPremiumModel()
    .premiumQuotas
    .collectAsState(initial = emptyList())
    .value

  LaunchedEffect(key1 = true) {
    val isPremium = productModel.isPremium(premiumQuotas)
    if (networkModel.checkConnection(context)) {
      if (noQuota) {
        trendsModel.getSearchModel().insertSearch()
        trendsModel
          .getHistoryModel()
          .addSearch(
            getCurrentDate(),
            country = "TRENDS", currency = "TRENDS", sizeType = "TRENDS",
            size = 0.0,
            trend.image,
            trend.name,
            ""
          )
        displayItem.value = true

      } else if (trendsModel.getSearchModel().dbLogic(quota!!) == 1 || isPremium) {
        trendsModel.getHistoryModel()
          .addSearch(
            getCurrentDate(),
            country = "TRENDS", currency = "TRENDS", sizeType = "TRENDS",
            size = 0.0,
            trend.image,
            trend.name,
            ""
          )
        if (!isPremium) {
          Toast
            .makeText(
              context,
              "${quota.search_limit - quota.search_number} free daily searches left.",
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
