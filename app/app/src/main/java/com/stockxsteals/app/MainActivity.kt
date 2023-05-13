package com.stockxsteals.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stockxsteals.app.view.compnents.main.SetupScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      val systemUiController = rememberSystemUiController()
      SideEffect { systemUiController.setSystemBarsColor(color = Color(0xFFFFFFFF)) }
      SetupScreen(navController = navController)
      }
    }
  }
