package com.stockxsteals.app.view.compnents.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.*
import com.stockxsteals.app.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AlternativeStartUpLogic(
  navController: NavHostController,
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
  networkModel: NetworkViewModel,
  uiModel: UIViewModel,
  windowSize: WindowSize
) {

  val context = LocalContext.current
  val trends = trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value
  var isPremium by remember { mutableStateOf(0) }


  val searchQuotaList = productModel
    .getSearchModel()
    .quota
    .collectAsState(initial = emptyList())
    .value

  val premiumQuota = productModel
    .getPremiumModel()
    .premiumQuotas
    .collectAsState(initial = emptyList())
    .value

  LaunchedEffect(true) {
    productModel.isPremium(premiumQuota)
    isPremium = productModel.getPremiumModel().getIsPremium(1)

    if (searchQuotaList.isNotEmpty()) {
      productModel.insertFirstSearch(searchQuotaList)
    }
  }

  LaunchedEffect(true) {
    if (networkModel.checkConnection(context)) {
      trendsModel.accessTrends(trends, context)
      navController.navigate("trends_route")
    } else {
      networkModel.toastMessage(context)
    }
  }

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(
      start = 80.dp,
      end = 80.dp,
    )) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top
    ) {

      Image(painter = painterResource(id = R.drawable.stockxsteals),
        contentDescription = "Logo",
        modifier = uiModel.loginScreenImageModifier(windowSize))

      Text(text = "Welcome To L8test.", fontSize = 22.sp, fontWeight = FontWeight.Light)
    }
  }
}