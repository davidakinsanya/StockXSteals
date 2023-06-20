package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stockxsteals.app.navigation.nested.loginGraph
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.navigation.nested.sneakerNavGraph
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun NavGraph(navController: NavHostController,
             productSearchViewModel: ProductSearchViewModel,
             settingModel: SettingViewModel,
             trendsModel: TrendsUIViewModel,
             networkModel: NetworkViewModel,
             uiModel: UIViewModel,
             windowSize: WindowSize
) {
  NavHost(navController = navController,
          startDestination = "trends_route", // TODO: Change to "login_route"
          route = "root_route") {

    loginGraph(navController = navController,
               networkModel = networkModel,
               trendsModel = trendsModel,
               uiModel = uiModel,
               windowSize = windowSize)


    sneakerNavGraph(navController = navController,
                    trendsModel = trendsModel,
                    productSearchViewModel = productSearchViewModel,
                    windowSize = windowSize)

    settingsNavGraph(navController = navController,
                     settingModel = settingModel,
                     trendsModel = trendsModel,
                     uiModel = uiModel,
                     windowSize = windowSize)
  }
}