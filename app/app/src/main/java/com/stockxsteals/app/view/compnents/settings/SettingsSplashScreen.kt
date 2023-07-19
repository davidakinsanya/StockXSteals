package com.stockxsteals.app.view.compnents.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.stockxsteals.app.model.ui.SettingScreens
import com.stockxsteals.app.model.ui.settingScreensList
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsSplashScreen(navController: NavHostController,
                         trendsModel: TrendsUIViewModel,
                         uiModel: UIViewModel,
                         settingModel: SettingViewModel,
                         windowSize: WindowSize
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current
  val trends = trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value

  var isPremium by remember { mutableStateOf(false) }

  LaunchedEffect(true) {
    isPremium = settingModel.getPremiumModel().getIsPremium(1) == 1
  }


  Scaffold {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
    ) {
      Row(modifier =
      Modifier
        .fillMaxWidth()
        .padding(start = 30.dp,
          bottom = uiModel.settingsPaddingSmall(windowSize),
          end = uiModel.searchEntryEndPaddingLarge(windowSize))
        .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = "Settings",
             fontWeight = FontWeight.ExtraBold,
             fontSize = 25.sp,
             modifier = Modifier.width(100.dp),
          textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            when (navController.previousBackStackEntry?.destination?.route) {
              AppScreens.SettingScreen.route -> {
                scope.launch { trendsModel.accessTrends(trends, context) }
                navController.navigate(AppScreens.Trends.route)
              }

              AppScreens.Premium.route -> {
                navController.navigate(AppScreens.Trends.route)
              }
              else -> {
                navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
              }
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
        .fillMaxWidth()
        .fillMaxHeight(0.9f)) {
          val settingScreens = settingScreensList()

          if (isPremium) {
            settingScreens.remove(SettingScreens.Upgrade)
            settingScreens.add(0, SettingScreens.Socials)
          }

        items(settingScreens.size) { item ->
             Row(modifier =
             Modifier
               .fillMaxWidth()
               .padding(
                 start = 45.dp,
                 bottom = 50.dp,
                 end = uiModel.settingEndPadding(windowSize))
               .height(50.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically) {

               Text(text = settingScreens[item].screen,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    modifier = Modifier.width(150.dp)
               )

               Spacer(Modifier.padding(uiModel.settingIconSpacerPadding(windowSize)))

               AsyncImage(
                 model = settingScreens[item].icon,
                 contentDescription = "Setting Logo",
                 modifier =
                 Modifier
                   .fillMaxSize(.5f)
                   .clickable {
                     when (settingScreens[item].screen) {
                       "Upgrade" -> {
                         navController.navigate(AppScreens.Premium.route)
                       }
                       else -> {
                         navController
                           .currentBackStackEntry
                           ?.savedStateHandle
                           ?.set(
                             "setting",
                             settingScreens[item].screen
                           )
                         navController.navigate(AppScreens.SettingScreen.route)
                       }
                     }
                   })
             }
          }
        }
    }
  }
}