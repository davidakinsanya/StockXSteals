package com.stockxsteals.app.view.compnents.login

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.payWallView
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
  val firebase = FirebaseAnalytics.getInstance(context)

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

  var showPaywall = false
  var isPremium = false

  LaunchedEffect(true) {
    isPremium = productModel.isPremium(premiumQuota)
    productModel.insertFirstSearch(searchQuotaList)
    if (searchQuotaList.isNotEmpty())
      showPaywall = productModel
        .getSearchModel()
        .paywallLock(
          searchQuotaList[0],
          premiumQuota[0].isPremium.toInt()) == 1
  }



  LaunchedEffect(true) {
    if ((showPaywall || trends.isEmpty()) && !isPremium) {
      payWallView(firebase)
      trendsModel.setTrendsHolding(trends)
      navController.navigate(AppScreens.Premium.route)
    } else {
      if (networkModel.checkConnection(context)) {
        Toast.makeText(context, "Welcome to L8test.", Toast.LENGTH_LONG).show()
        trendsModel.accessTrends(trends, context)
      } else {
        networkModel.toastMessage(context)
      }
      navController.navigate("trends_route")
    }
  }
}