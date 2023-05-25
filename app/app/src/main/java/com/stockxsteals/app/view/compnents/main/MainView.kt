package com.stockxsteals.app.view.compnents.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.navigation.NavGraph
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.FilterPresetsViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.ui.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupScreen(navController: NavHostController) {

  val presetsModel: FilterPresetsViewModel = hiltViewModel()
  val dailySearchModel: DailySearchViewModel = hiltViewModel()
  val premiumModel: PremiumViewModel = hiltViewModel()
  val historyModel: DailySearchHistoryViewModel = hiltViewModel()

  val filterModel = FilterViewModel(presetsModel)
  val uiModel: UIViewModel = viewModel()
  val qonversionModel: QonversionViewModel = viewModel()
  val networkModel: NetworkViewModel = viewModel()

  val settingModel = SettingViewModel(qonversionModel,
                                      premiumModel,
                                      historyModel)

  val trendsModel = TrendsViewModel(LocalContext.current,
                                    networkModel,
                                    historyModel,
                                    dailySearchModel,
                                    premiumModel)

  val productSearchModel = ProductSearchViewModel(filterModel = filterModel,
                                                  historyModel = historyModel,
                                                  premiumModel = premiumModel,
                                                  searchModel = dailySearchModel,
                                                  uiModel = uiModel)


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    uiModel.listOfScreens().forEach { _ ->
      val bool = currentDestination?.hierarchy?.any {
      uiModel.bottomNavBool().contains(it.route)
    } == true

    val isNull = currentDestination?.route == null
    val isLogin = currentDestination?.route == AppScreens.Login.route


    Scaffold(
      topBar = { if (!isLogin && !isNull) SearchAppBar(navController = navController,
                                                       productSearchViewModel = productSearchModel,
                                                       networkModel = networkModel) },

      bottomBar = { if (!bool) BottomBar(navController = navController) }
    ) {
      NavGraph(
        navController = navController,
        productSearchViewModel = productSearchModel,
        settingModel = settingModel,
        trendsModel = trendsModel,
        networkModel = networkModel)
    }
  }
}