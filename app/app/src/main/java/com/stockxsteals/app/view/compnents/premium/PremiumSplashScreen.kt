package com.stockxsteals.app.view.compnents.premium

import android.annotation.SuppressLint
import android.widget.Toast
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
import com.stockxsteals.app.ui_coroutines.SearchEntryCoroutineDB
import com.stockxsteals.app.ui_coroutines.TrendCoroutineDB
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PremiumSplashScreen(
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
  settingModel: SettingViewModel,
  navController: NavHostController,
  windowSize: WindowSize,
  result: List<String>?
) {

  val uiModel = productModel.getUIModel()

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
            productModel = productModel,
            trendsModel = trendsModel,
            settingModel = settingModel,
            navController = navController,
            result = result
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
      contentDescription = "selling point Icon",
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
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
  settingModel: SettingViewModel,
  navController: NavHostController,
  result: List<String>?
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current
  val nextAction = remember { mutableStateOf(false) }

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
      .clickable {},
      horizontalArrangement = Arrangement.Center) {
      Text(text = "Upgrade For ~$12/mo",
           fontSize = 12.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
             .align(Alignment.CenterVertically)
             .padding(16.dp)
             .clickable {
               if (paymentFlow(scope, settingModel, context) == 1)
                 nextAction.value = true
             }
      )
    }
    if (navController.previousBackStackEntry?.destination?.route == AppScreens.Settings.route )
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
    if (navController.previousBackStackEntry?.destination?.route == AppScreens.Login.route)
      Text(
        text = "Continue",
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        textDecoration = TextDecoration.Underline,
        modifier =
        Modifier
          .padding(top = 10.dp)
          .clickable {
            scope.launch {
              trendsModel.accessTrends(trendsModel.getTrendsHolding(), context)
              navController.navigate("trends_route")
              Toast.makeText(context, "Go to 'Tutorial' in Settings for a demo video.", Toast.LENGTH_SHORT).show()
            }
          }
      )

    if (nextAction.value)
      NextAction(
        navController = navController,
        productModel = productModel,
        trendsModel = trendsModel,
        result = result
      )
  }
}

@Composable
fun NextAction(navController: NavHostController,
               productModel: ProductSearchViewModel,
               trendsModel: TrendsUIViewModel,
               result: List<String>?
) {
  val trueState = remember { mutableStateOf(true) }
  val context = LocalContext.current

  when (navController.previousBackStackEntry?.destination?.route) {
      AppScreens.Trends.route -> {
        TrendCoroutineDB(
          displayItem = trueState,
          productModel = productModel,
          trend = productModel.getCurrentTrends(),
          navController = navController,
          context = context,
          searchQuota = productModel.getDailySearchQuota(),
        )
        productModel.clearTrends()
        productModel.clearQuota()
     }

     AppScreens.SneakerSearch.route -> {
       SearchEntryCoroutineDB(
         displayItem = trueState,
         productModel = productModel,
         result = result!!,
         navController = navController,
         context = context,
         searchQuota = productModel.getDailySearchQuota()
       )
       productModel.clearQuota()
     }

    AppScreens.Login.route -> {
      LaunchedEffect(key1 = true) {
        trendsModel.accessTrends(trendsModel.getTrendsHolding(), context)
        navController.navigate("trends_route")
      }
      trendsModel.clearTrendsHolding()
    }

   AppScreens.Settings.route -> {
     navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
   }
 }
}