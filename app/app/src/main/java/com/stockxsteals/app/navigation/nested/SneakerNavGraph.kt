package com.stockxsteals.app.navigation.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.*
import com.stockxsteals.app.view.compnents.main_search_page.SneakerViewComponent
import com.stockxsteals.app.view.compnents.search_results_page.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.top_search.SearchScreen
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.sneakerNavGraph(
  navController: NavHostController,
  trendsModel: TrendsUIViewModel,
  productSearchViewModel: ProductSearchViewModel,
  windowSize: WindowSize
) {
  navigation(
    startDestination = AppScreens.Trends.route,
    route = "trends_route"
  ) {

    composable(route = AppScreens.Trends.route,
               enterTransition = rightEnterTransition) {
      TrendsViewComponent(
        navController = navController,
        trendsModel = trendsModel,
        productModel = productSearchViewModel,
        windowSize = windowSize
      )
    }

    composable(route = AppScreens.Search.route,
               enterTransition = leftEnterTransition) {
        SneakerViewComponent(
          productModel = productSearchViewModel,
          navController = navController,
          windowSize = windowSize
        )
    }

    composable(route = AppScreens.TopSearch.route,
               enterTransition = downEnterTransition,
               exitTransition = upExitTransition) {
      SearchScreen(
        navController = navController,
        productModel = productSearchViewModel,
        windowSize = windowSize
      )
    }

    composable(route = AppScreens.SneakerSearch.route,
               enterTransition = leftEnterTransition) {
      SneakerSplashScreen(
        navController = navController,
        productModel = productSearchViewModel,
        networkModel = trendsModel.getNetworkModel(),
        windowSize = windowSize
      )
    }
  }
}