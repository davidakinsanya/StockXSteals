package com.stockxsteals.app.view.compnents.premium

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.stockxsteals.app.R
import com.stockxsteals.app.model.ui.PremiumSellingPoint
import com.stockxsteals.app.model.ui.sellingPointsList
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel
import db.entity.Trends
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PremiumSplashScreen(
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
  settingModel: SettingViewModel,
  navController: NavHostController,
  windowSize: WindowSize,
) {

  val uiModel = productModel.getUIModel()
  val trends = trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value
  val scope = rememberCoroutineScope()

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(top = 5.dp)
    .background(color = Color(250, 240, 250))) {
      Column(modifier =
      Modifier
        .fillMaxSize()
        .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
          PremiumTopRow()
          MainBody(uiModel, windowSize)
          UpgradeButton(
            settingModel = settingModel,
            trendsModel = trendsModel,
            navController = navController,
            scope = scope,
            trends = trends
          )
      }
  }
}

@Composable
fun PremiumTopRow() {
  Column(modifier =
  Modifier
    .fillMaxWidth()
    .height(120.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(painter = painterResource(R.drawable.stockxsteals),
      contentDescription = "Logo",
      modifier = Modifier
        .fillMaxWidth(0.8f)
        .fillMaxHeight(0.8f)
        .graphicsLayer { alpha = .9f })

    Text(text = "Upgrade To L8test+", fontSize = 18.sp, fontWeight = FontWeight.Light)
  }
}
@Composable
fun MainBody(uiModel: UIViewModel,
             windowSize: WindowSize) {
  val list = sellingPointsList()

  LazyColumn(modifier = uiModel.premiumScreenMainBodyModifier(windowSize),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center) {
    items(list.size) { SellingPointRow(list[it]) }
  }
}

@Composable
fun SellingPointRow(sellingPoint: PremiumSellingPoint) {
  Row (modifier =
  Modifier
    .padding(16.dp)
    .height(100.dp)) {
    AsyncImage(
      model = sellingPoint.icon,
      contentDescription = "Selling Point Icon",
      modifier = Modifier
        .fillMaxHeight(0.5f)
    )

    Column(horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.Center,
           modifier =
           Modifier
             .fillMaxWidth()
             .padding(start = 20.dp, end = 20.dp)) {

        Text(
          text = sellingPoint.title,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
          text = sellingPoint.description,
          fontSize = 12.sp,
          modifier = Modifier
            .height(150.dp)
            .verticalScroll(state = rememberScrollState())
        )
    }
  }
}

@Composable
fun UpgradeButton(
  settingModel: SettingViewModel,
  trendsModel: TrendsUIViewModel,
  navController: NavHostController,
  scope: CoroutineScope,
  trends: List<Trends>
) {

  val context = LocalContext.current

  Column(
    Modifier
      .fillMaxWidth()
      .padding(bottom = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Row(modifier =
    Modifier
      .fillMaxWidth(0.6f)
      .padding(bottom = 10.dp)
      .background(color = Color.Transparent)
      .border(
        border = BorderStroke(
          width = 2.dp,
          color = Color.Black
        ),
        shape = RoundedCornerShape(50.dp)
      )
      .clickable {
        paymentFlow(
          settingModel,
          trendsModel,
          navController,
          context,
          scope,
          trends
        )
      },
      horizontalArrangement = Arrangement.Center) {
      Text(text = "Upgrade For ~$2.99/wk",
           fontSize = 12.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
             .align(Alignment.CenterVertically)
             .padding(16.dp)
      )
    }

    if (navController.previousBackStackEntry?.destination?.route == AppScreens.Settings.route) {
      Text(
        text = "Back",
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        textDecoration = TextDecoration.Underline,
        modifier =
        Modifier
          .padding(top = 10.dp)
          .clickable {
            navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
          }
      )
    } else {
      Text(
        text = "Less than a coffee a week.",
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        textDecoration = TextDecoration.Underline,
        modifier =
        Modifier.padding(top = 10.dp)
      )
    }
  }
}