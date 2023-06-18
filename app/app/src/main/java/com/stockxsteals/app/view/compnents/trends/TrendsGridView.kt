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
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@Composable
fun TrendsViewComponent(trendsModel: TrendsUIViewModel,
                        productModel: ProductSearchViewModel,
                        navController: NavHostController,
) {

  val currentTrends: List<Trend> = trendsModel.bootTrends.collectAsState().value

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(229, 255, 229))
  ) {
    Column(
      modifier = Modifier
        .padding(top = 30.dp) // 5" or smaller add bottom padding (bottom = 30.dp)
        .fillMaxHeight(.88f)
    ) {

      TrendsLazyGrid(trends = currentTrends,
                     trendsModel = trendsModel,
                     productModel =  productModel,
                     navController = navController)
    }
  }
}