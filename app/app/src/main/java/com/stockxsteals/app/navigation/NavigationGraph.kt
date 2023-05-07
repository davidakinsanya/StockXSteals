package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.topsearch.SearchScreen
import com.stockxsteals.app.view.compnents.settings.SettingsSplashScreen
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.FilterViewModel
import com.stockxsteals.app.viewmodel.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.UIViewModel

@Composable
fun NavGraph(navController: NavHostController,
             filterModel: FilterViewModel,
             uiModel: UIViewModel
) {
  NavHost(navController = navController, startDestination = AppScreens.Trends.route) {
    composable(route = AppScreens.Trends.route) {
      TrendsViewComponent()
    }
    composable(route = AppScreens.Search.route) {
      val productModel = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<ProductSearchViewModel>("productModel")

      if (productModel != null)
        SneakerViewComponent(productModel = productModel)
      else SneakerViewComponent(productModel = null)
    }

    composable(route = AppScreens.Settings.route) {
      SettingsSplashScreen(navController = navController)
    }

    composable(route = AppScreens.TopSearch.route) {
      SearchScreen(navController = navController, model = filterModel, uiModel = uiModel)
    }

    composable(route = AppScreens.SneakerSearch.route) {
      val model = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<FilterViewModel>("filterModel")

      if (model != null)
        SneakerSplashScreen(navController = navController, model = model)
    }

  }
}