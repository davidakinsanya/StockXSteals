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
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsViewModel

fun NavGraphBuilder.sneakerNavGraph(
  trendsModel: TrendsViewModel,
  productSearchViewModel: ProductSearchViewModel,
  networkModel: NetworkViewModel,
  navController: NavHostController) {
  navigation(
    startDestination = AppScreens.Trends.route,
    route = "trends_route"
  ) {
    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent(trendsModel = trendsModel,
                          networkModel = networkModel)
    }

    composable(route = AppScreens.Search.route) {
      val productModel = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<ProductSearchViewModel>("productModel")

      if (productModel != null)
        SneakerViewComponent(productModel = productModel,
          uiModel = productSearchViewModel.getUIModel())
      else SneakerViewComponent(productModel = null,
        uiModel = productSearchViewModel.getUIModel())
    }
  }

  composable(route = AppScreens.TopSearch.route) {
    SearchScreen(navController = navController,
      productSearchViewModel = productSearchViewModel)
  }

  composable(route = AppScreens.SneakerSearch.route) {
    val model = navController
      .previousBackStackEntry
      ?.savedStateHandle
      ?.get<ProductSearchViewModel>("productSearchViewModel")

    if (model != null)
      SneakerSplashScreen(navController = navController,
        productSearchViewModel = model)
  }
}