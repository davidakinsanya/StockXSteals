package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

fun NavGraphBuilder.sneakerGraph(navController: NavHostController, uiModel: UIViewModel) {
  navigation(startDestination = AppScreens.Trends.route,
             route = "trends_route") {
    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent()
    }
    composable(route = AppScreens.Search.route) {
      val productModel = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<ProductSearchViewModel>("productModel")

      if (productModel != null)
        SneakerViewComponent(productModel = productModel, uiModel = uiModel)
      else SneakerViewComponent(productModel = null, uiModel = uiModel)
    }
  }

}