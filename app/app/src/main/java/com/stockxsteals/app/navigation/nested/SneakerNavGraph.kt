package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.topsearch.SearchScreen
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

fun NavGraphBuilder.sneakerNavGraph(
  trendsModel: TrendsUIViewModel,
  productSearchViewModel: ProductSearchViewModel,
  navController: NavHostController) {
  navigation(
    startDestination = AppScreens.Trends.route,
    route = "trends_route"
  ) {
    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent(trendsModel = trendsModel)
    }

    composable(route = AppScreens.Search.route) {
      /*
      val productModel = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<ProductSearchViewModel>("productModel") */

      SneakerViewComponent(productModel = productSearchViewModel,
          uiModel = productSearchViewModel.getUIModel())
    }
  }

  composable(route = AppScreens.TopSearch.route) {
    SearchScreen(navController = navController,
      productSearchViewModel = productSearchViewModel)
  }

  composable(route = AppScreens.SneakerSearch.route) {
    /* val model = navController
      .previousBackStackEntry
      ?.savedStateHandle
      ?.get<ProductSearchViewModel>("productSearchViewModel")
     */


      SneakerSplashScreen(navController = navController,
                          productSearchViewModel = productSearchViewModel,
                          networkModel = trendsModel.getNetworkModel())
  }
}