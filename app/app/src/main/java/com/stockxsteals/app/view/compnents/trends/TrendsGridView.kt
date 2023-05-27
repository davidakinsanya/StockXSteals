package com.stockxsteals.app.view.compnents.trends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.beust.klaxon.Klaxon
import com.stockxsteals.app.http.doRequest
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@Composable
fun TrendsViewComponent(trendsModel: TrendsUIViewModel) {

  val currentTrends: List<Trend>

  when (trendsModel.getFileState()) {
    1 -> {
      currentTrends = doRequest(model = trendsModel,
                                type = "sneakers",
                                currency = "EUR",
                                int = trendsModel.getFileState())
    }

    0 -> {
      currentTrends = doRequest(model = trendsModel,
                                type = "sneakers",
                                currency = "EUR",
                                int = trendsModel.getFileState())
    }

    -1 -> {
      currentTrends = Klaxon().parse<List<Trend>>(trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value[0].json)!!
    }

    else -> {
      currentTrends = listOf()
    }
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