package com.stockxsteals.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.stockxsteals.app.navigation.nested.loginGraph
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.navigation.nested.sneakerNavGraph
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController,
             productSearchViewModel: ProductSearchViewModel,
             settingModel: SettingViewModel,
             trendsModel: TrendsUIViewModel,
             networkModel: NetworkViewModel,
             uiModel: UIViewModel,
             windowSize: WindowSize
) {
  AnimatedNavHost(navController = navController,
          startDestination = "login_route", // TODO: Change to "login_route"
          route = "root_route") {

    loginGraph(navController = navController,
               networkModel = networkModel,
               trendsModel = trendsModel,
               uiModel = uiModel,
               windowSize = windowSize)


    sneakerNavGraph(navController = navController,
                    trendsModel = trendsModel,
                    productSearchViewModel = productSearchViewModel,
                    settingModel = settingModel,
                    windowSize = windowSize)

    settingsNavGraph(navController = navController,
                     settingModel = settingModel,
                     trendsModel = trendsModel,
                     uiModel = uiModel,
                     windowSize = windowSize)
  }
}