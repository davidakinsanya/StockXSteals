package com.stockxsteals.app.view.compnents.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stockxsteals.app.navigation.NavGraph
import com.stockxsteals.app.viewmodel.db.FilterPresetsViewModel
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupScreen(navController: NavHostController) {

  val presetsModel: FilterPresetsViewModel = hiltViewModel()
  val filterModel = FilterViewModel(presetsModel)

  val uiModel = UIViewModel()
  var selected = ""

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  uiModel.listOfScreens().forEach { _ ->
    val bool = currentDestination?.hierarchy?.any {
      uiModel.bottomNavBool().contains(it.route)
    } == true

    Scaffold(
      topBar = { SearchAppBar(navController = navController, filterModel = filterModel, uiModel = uiModel) },
      bottomBar = { if (!bool) BottomBar(navController = navController) }
    ) {
      NavGraph(
        navController = navController,
        filterModel = filterModel,
        uiModel = uiModel)
    }
  }
}