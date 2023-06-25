package com.stockxsteals.app

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stockxsteals.app.view.compnents.main.SetupScreen

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    setContent {
      val navController = rememberAnimatedNavController()
      val systemUiController = rememberSystemUiController()
      SideEffect { systemUiController.setSystemBarsColor(color = Color(0xFFFFFFFF)) }
      SetupScreen(navController = navController)
      }
    }
  }
