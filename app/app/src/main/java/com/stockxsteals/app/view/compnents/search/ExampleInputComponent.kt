package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens

@Composable
fun ExampleInput(type: String) {
  Column(modifier = Modifier.padding(16.dp)) {
    when (type) {
      "Search By Code" -> {
        Text(
          modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 50.dp, end = 50.dp),
          fontSize = 12.sp,
          text = "Example of a valid code is: 'CT5053-001'.\n(Nike SB Dunk Low Travis Scott)",
          fontWeight = FontWeight.Light,
          fontFamily = FontFamily.SansSerif,
          textAlign = TextAlign.Center
        )
      }
      "Search By Slug" -> {
        Text(
          modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 20.dp),
          text = "Example of a valid slug is: 'nike-air-max-1-travis-scott-wheat'. \n (Nike SB Dunk Low Travis Scott)",
          fontSize = 12.sp,
          fontWeight = FontWeight.Light,
          fontFamily = FontFamily.SansSerif,
          textAlign = TextAlign.Center
        )
      }
    }
  }
}

@Composable
fun DisplayExampleMessage(navController: NavHostController) {
  when (navController.previousBackStackEntry?.destination?.route) {
    AppScreens.SearchByCode.route -> {
      ExampleInput(type = "Search By Code")
    }
    AppScreens.SearchBySlug.route -> {
      ExampleInput(type = "Search By Slug")
    }
  }
}