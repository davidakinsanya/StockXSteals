package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.navigation.nested.searchGraph
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.topsearch.SearchScreen
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun NavGraph(navController: NavHostController,
             productSearchViewModel: ProductSearchViewModel,
             settingModel: SettingViewModel,
             trendsModel: TrendsViewModel
) {
  NavHost(navController = navController,
          startDestination = AppScreens.Trends.route,
          route = "root_route") {

    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent(trendsModel = trendsModel)
    }

    composable(route = AppScreens.Search.route) {
      val productModel = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<ProductSearchViewModel>("productModel")

      if (productModel != null)
        SneakerViewComponent(productModel = productModel, uiModel = productSearchViewModel.getUIModel())
      else SneakerViewComponent(productModel = null, uiModel = productSearchViewModel.getUIModel())
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
        SneakerSplashScreen(navController = navController, productSearchViewModel = model)
    }

    // sneakerGraph(navController, productSearchViewModel = productSearchViewModel)
    settingsNavGraph(navController, settingModel)
    //searchGraph(navController, productSearchViewModel)
  }
}