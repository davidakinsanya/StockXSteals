package com.stockxsteals.app.view.compnents.trends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@Composable
fun TrendsViewComponent(navController: NavHostController,
                        trendsModel: TrendsUIViewModel,
                        productModel: ProductSearchViewModel,
                        windowSize: WindowSize

) {

  val currentTrends: List<Trend> = trendsModel.bootTrends.collectAsState().value
  val uiModel = productModel.getUIModel()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(229, 255, 229))
  ) {
    Column(
      modifier = Modifier
        .padding(top = uiModel.trendsGridViewTopPadding(windowSize),
                 bottom = uiModel.trendsGridViewSmallPadding(windowSize))
        .fillMaxHeight(.88f)
    ) {
        TrendsLazyGrid(navController = navController,
                       trendsModel = trendsModel,
                       productModel =  productModel,
                       uiModel = uiModel,
                       trends = currentTrends,
                       windowSize = windowSize)
    }
  }
}