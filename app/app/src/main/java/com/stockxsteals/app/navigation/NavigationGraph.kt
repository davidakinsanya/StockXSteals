package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun NavGraph(navController: NavHostController,
             filterModel: FilterViewModel,
             uiModel: UIViewModel
) {
  NavHost(navController = navController,
          startDestination = AppScreens.Trends.route,
          route = "root_route") {

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

    // sneakerGraph(navController, uiModel)
    settingsNavGraph(navController)
    searchGraph(navController, filterModel, uiModel)
  }
}