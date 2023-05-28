package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.main_search_page.SneakerViewComponent
import com.stockxsteals.app.view.compnents.search_results_page.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.search_bar.SearchScreen
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
      // check if search state is populated
      SneakerViewComponent(productModel = null,
                           uiModel = productSearchViewModel.getUIModel())
    }
  }

  composable(route = AppScreens.TopSearch.route) {
    SearchScreen(navController = navController,
      productSearchViewModel = productSearchViewModel)
  }

  composable(route = AppScreens.SneakerSearch.route) {
    SneakerSplashScreen(navController = navController,
        productSearchViewModel = productSearchViewModel,
        networkModel = trendsModel.getNetworkModel())
  }
}