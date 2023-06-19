package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.main_search_page.SneakerViewComponent
import com.stockxsteals.app.view.compnents.search_results_page.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.top_search.SearchScreen
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

fun NavGraphBuilder.sneakerNavGraph(
  navController: NavHostController,
  trendsModel: TrendsUIViewModel,
  productSearchViewModel: ProductSearchViewModel,
  uiModel: UIViewModel) {
  navigation(
    startDestination = AppScreens.Trends.route,
    route = "trends_route"
  ) {

    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent(
        trendsModel = trendsModel,
        productModel = productSearchViewModel,
        navController = navController
      )
    }

    composable(route = AppScreens.Search.route) {
      // check if search state is populated
        SneakerViewComponent(
          productModel = productSearchViewModel,
          uiModel = productSearchViewModel.getUIModel(),
          navController = navController
        )
    }

    composable(route = AppScreens.TopSearch.route) {
      SearchScreen(
        navController = navController,
        productSearchViewModel = productSearchViewModel
      )
    }

    composable(route = AppScreens.SneakerSearch.route) {
      SneakerSplashScreen(
        navController = navController,
        productSearchViewModel = productSearchViewModel,
        networkModel = trendsModel.getNetworkModel()
      )
    }
  }
}