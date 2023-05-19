package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.navigation.nested.searchGraph
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.view.compnents.searchpage.SneakerViewComponent
import com.stockxsteals.app.view.compnents.trends.TrendsViewComponent
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun NavGraph(navController: NavHostController,
             filterModel: FilterViewModel,
             uiModel: UIViewModel,
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
        SneakerViewComponent(productModel = productModel, uiModel = uiModel)
      else SneakerViewComponent(productModel = null, uiModel = uiModel)
    }

    // sneakerGraph(navController, uiModel, trendsModel)
    settingsNavGraph(navController, settingModel)
    searchGraph(navController, filterModel, uiModel)
  }
}