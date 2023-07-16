package com.stockxsteals.app.navigation.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.view.compnents.login.AlternativeStartUpLogic
import com.stockxsteals.app.viewmodel.ui.*

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginGraph(navController: NavHostController,
                               signInModel: SignInViewModel,
                               productModel: ProductSearchViewModel,
                               networkModel: NetworkViewModel,
                               trendsModel: TrendsUIViewModel,
                               uiModel: UIViewModel,
                               windowSize: WindowSize
) {
  navigation(startDestination = AppScreens.Login.route,
             route = "login_route") {

    composable(route = AppScreens.Login.route) {
      AlternativeStartUpLogic(
        navController = navController,
        productModel = productModel,
        trendsModel = trendsModel,
      )
    }
  }
}