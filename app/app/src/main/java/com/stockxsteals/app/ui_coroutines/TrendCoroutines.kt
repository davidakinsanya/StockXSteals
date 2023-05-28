package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import db.entity.DailySearchQuota

@Composable
fun TrendCoroutineOnClick(trendsModel: TrendsUIViewModel,
                          networkModel: NetworkViewModel,
                          context: Context,
                          noQuota: Boolean,
                          trend: Trend,
                          quota: DailySearchQuota?,
                          displayItem: MutableState<Boolean>
                          ) {

  LaunchedEffect(key1 = 1) {
    val isPremium = trendsModel.getPremiumModel().getIsPremium() == 1
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
      } // navigation

    } else {
      networkModel.toastMessage(context)
    }
  }
}