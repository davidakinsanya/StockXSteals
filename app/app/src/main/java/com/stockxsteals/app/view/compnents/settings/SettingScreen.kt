package com.stockxsteals.app.view.compnents.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.viewmodel.ui.SettingViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(setting: String,
                  settingModel: SettingViewModel,
                  navController: NavHostController) {

  Scaffold {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
    ) {
      Row(
        modifier =
        Modifier
          .fillMaxWidth()
          .padding(bottom = 50.dp)
          .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
      ) {

        Text(
          text = setting,
          fontWeight = FontWeight.ExtraBold,
          fontSize = 20.sp,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
          }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon"
          )
        }
      }
     SettingPage(setting, settingModel)
    }
  }
}

@Composable
fun SettingPage(setting: String, settingModel: SettingViewModel) {
  when (setting) {
    "Search History" -> {

    }

    "About Us" -> {

    }
  }
}