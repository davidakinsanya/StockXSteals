package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.view.compnents.topsearch.SearchScreen
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel

fun NavGraphBuilder.searchGraph(navController: NavHostController,
                                productSearchViewModel: ProductSearchViewModel) {

  navigation(startDestination = AppScreens.TopSearch.route,
             route = "top_search_route") {

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
  }

}