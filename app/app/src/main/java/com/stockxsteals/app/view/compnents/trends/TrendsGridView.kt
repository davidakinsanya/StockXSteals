package com.stockxsteals.app.view.compnents.trends


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.fileIsOld
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@Composable
fun TrendsViewComponent(trendsModel: TrendsUIViewModel) {
  val currentTrends: List<Trend>?
  val trend = trendsModel.getDBTrend()

  // check timestamp exists or is old
  if (trend.timestamp.isEmpty() || (trend.timestamp.isNotEmpty() && fileIsOld(trend.timestamp))) {
    currentTrends = trendsModel.bootTrends.collectAsState().value
  } else {
    currentTrends = listOf()
  }

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