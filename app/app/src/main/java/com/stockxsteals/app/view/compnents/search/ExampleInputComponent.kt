package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.CustomText

@Composable
fun ExampleInput(type: String) {
  Column(modifier = Modifier.padding(start = 16.dp, top = 75.dp)) {
    when (type) {
      "Search By Code" -> {
        CustomText().AppendCustomText(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 50.dp, end = 50.dp),
          text = "Example of a valid code is: **'CT5053-001'**.\n(Nike SB Dunk Low Travis Scott)",
        )
      }
      "Search By Slug" -> {
        CustomText().AppendCustomText(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end  = 50.dp),
          text = "Example of a valid slug is: \n **'nike-air-max-1-travis-scott-wheat'**. \n (Nike SB Dunk Low Travis Scott)",
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