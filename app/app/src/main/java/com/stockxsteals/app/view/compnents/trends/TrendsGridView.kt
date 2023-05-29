package com.stockxsteals.app.view.compnents.trends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.ui_coroutines.AddTrend
import com.stockxsteals.app.ui_coroutines.bootTrends
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@Composable
fun TrendsViewComponent(trendsModel: TrendsUIViewModel) {

  val context = LocalContext.current
  val trendsList = bootTrends(trendsModel = trendsModel, context = context)
  val bool = trendsList.isNotEmpty()
  AddTrend(boolean = bool, trendsModel = trendsModel, trend = trendsList)
  val currentTrends: List<Trend> = trendsModel.bootTrends.collectAsState().value

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(229, 255, 229))
  ) {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
        .fillMaxHeight(.88f)
    ) {

      TrendsLazyGrid(currentTrends, trendsModel)

    }
  }
}