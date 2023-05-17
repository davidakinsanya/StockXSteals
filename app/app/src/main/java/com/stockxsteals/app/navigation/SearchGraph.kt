package com.stockxsteals.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.topsearch.SearchScreen
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

fun NavGraphBuilder.searchGraph(navController: NavHostController,
                                filterModel: FilterViewModel,
                                uiModel: UIViewModel) {

  navigation(startDestination = AppScreens.TopSearch.route,
             route = "top_search_route") {

    composable(route = AppScreens.TopSearch.route) {
      SearchScreen(navController = navController, filterModel, uiModel = uiModel)
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