package com.stockxsteals.app.view.compnents.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsSplashScreen(navController: NavHostController) {
  Scaffold {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
    ) {
      Row(modifier =
      Modifier
        .fillMaxWidth()
        .padding(bottom = 100.dp)
        .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = "Settings",
             fontWeight = FontWeight.ExtraBold,
             fontSize = 25.sp,
          textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            if (navController.previousBackStackEntry?.destination?.route == "setting_screen") {
              navController.navigate(AppScreens.Trends.route)
            } else {
              navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
            }
          }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon"
          )
        }
      }

      LazyColumn(modifier =
      Modifier
        .fillMaxHeight(0.9f)) {

          val settingScreens = listOf(
            "Search History",
            "Upgrade",
            "About Us",
            "Log Out"
          )

          items(settingScreens.size) { item ->
             Row(modifier =
             Modifier
               .padding(bottom = 50.dp)
               .fillMaxWidth()
               .height(50.dp),
               horizontalArrangement = Arrangement.SpaceAround,
               verticalAlignment = Alignment.CenterVertically) {

               Text(text = settingScreens[item],
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    modifier = Modifier.width(150.dp)
               )

               Spacer(modifier = Modifier.padding(25.dp))

               IconButton(
                 onClick = {
                   navController
                     .currentBackStackEntry
                     ?.savedStateHandle
                     ?.set (
                       "setting",
                       settingScreens[item]
                     )
                   navController.navigate(AppScreens.SettingScreen.route)
                 }) {
                 Icon(
                   imageVector = Icons.Default.Info,
                   contentDescription = "Back Icon",
                 )
               }
             }
          }
        }

    }
  }
}