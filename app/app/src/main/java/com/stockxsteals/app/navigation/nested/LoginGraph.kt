package com.stockxsteals.app.navigation.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.login.AlternativeStartUpLogic
import com.stockxsteals.app.viewmodel.ui.*

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginGraph(navController: NavHostController,
                               productModel: ProductSearchViewModel,
                               networkModel: NetworkViewModel,
                               trendsModel: TrendsUIViewModel,
) {
  navigation(startDestination = AppScreens.Login.route,
             route = "login_route") {

    composable(route = AppScreens.Login.route) {
      AlternativeStartUpLogic(
        navController = navController,
        productModel = productModel,
        trendsModel = trendsModel,
        networkModel = networkModel
      )
    }
  }
}