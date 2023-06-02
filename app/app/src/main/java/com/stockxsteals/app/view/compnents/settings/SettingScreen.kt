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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(setting: String,
                  settingModel: SettingViewModel,
                  navController: NavHostController,
                  trendsModel: TrendsUIViewModel) {

  val scope = rememberCoroutineScope()

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
          modifier = Modifier.width(100.dp),
          fontSize = 23.sp,
          textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            val route = navController.previousBackStackEntry?.destination?.route!!
            if (route == AppScreens.Trends.route) {
              scope.launch { trendsModel.accessTrends() }
            }
            navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
          }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon"
          )
        }
      }
     SettingPage(setting, settingModel, navController)
    }
  }
}

@Composable
fun SettingPage(setting: String, settingModel: SettingViewModel, navController: NavHostController) {
  when (setting) {
    "Searches" -> {
      Searches(settingModel)
    }

    "About Us" -> {
      AboutUs()
    }

    "T&Cs" -> {
      TermsAndConditions()
    }

    "Log Out" -> {
      navController.navigate("root_route")
    }
  }
}