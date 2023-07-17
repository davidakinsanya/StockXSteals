package com.stockxsteals.app.view.compnents.login

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun AlternativeStartUpLogic(
  navController: NavHostController,
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
  networkModel: NetworkViewModel
) {

  val context = LocalContext.current
  val trends = trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value

  val premiumQuota = productModel
    .getPremiumModel()
    .premiumQuotas
    .collectAsState(initial = emptyList())
    .value

  val searchQuotaList = productModel
    .getSearchModel()
    .quota
    .collectAsState(initial = emptyList())
    .value

  LaunchedEffect(true) {
    productModel.isPremium(premiumQuota)
    productModel.insertFirstSearch(searchQuotaList)
  }

  LaunchedEffect(true) {
    if (networkModel.checkConnection(context)) {
      trendsModel.accessTrends(trends, context)
    } else {
      networkModel.toastMessage(context)
    }
    navController.navigate("trends_route")
  }
}