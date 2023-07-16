package com.stockxsteals.app.view.compnents.login

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.analytics.FirebaseAnalytics
import com.stockxsteals.app.R
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.utils.payWallView
import com.stockxsteals.app.viewmodel.ui.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController,
                signInModel: SignInViewModel,
                productModel: ProductSearchViewModel,
                networkModel: NetworkViewModel,
                trendsModel: TrendsUIViewModel,
                uiModel: UIViewModel,
                windowSize: WindowSize
) {


  val context = LocalContext.current
  val state by signInModel.state.collectAsState()
  val scope = rememberCoroutineScope()

  val trends = trendsModel.getTrendsModel().trends.collectAsState(initial = emptyList()).value
  val mauve = Color(224, 176, 255)
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

  val googleAuthUiClient by lazy {
    GoogleAuthUiClient(
      oneTapClient = Identity.getSignInClient(context as Activity)
    )
  }

  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartIntentSenderForResult(),
    onResult = { result ->
      if(result.resultCode == RESULT_OK) {
        scope.launch {
          val signInResult = googleAuthUiClient.signInWithIntent(
            intent = result.data ?: return@launch
          )
          signInModel.onSignInResult(signInResult)
        }
      }
    }
  )

  LaunchedEffect(key1 = state.isSignInSuccessful) {
    if(state.isSignInSuccessful) {
      scope.launch {
        if ((showPaywall || trends.isEmpty()) && !isPremium) {
          payWallView(firebase)
          trendsModel.setTrendsHolding(trends)
          navController.navigate(AppScreens.Premium.route)
        } else {
          trendsModel.accessTrends(trends, context)
          navController.navigate("trends_route")
        }
      }
      signInModel.resetState()
    }
  }

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(
      top = uiModel.loginScreenTopSmall(windowSize),
      start = 80.dp,
      end = 80.dp,
      bottom = uiModel.loginScreenBottomLarge(windowSize)
    )) {
    Column(modifier = uiModel.fillMaxSizeLarge(windowSize),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {

      Image(painter = painterResource(R.drawable.stockxsteals),
        contentDescription = "Logo",
        modifier = uiModel.loginScreenImageModifier(windowSize))

        Spacer(modifier = Modifier
          .padding(bottom = uiModel.loginScreenImagePadding(windowSize)))

      Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
              .width(300.dp)
              .clickable {
                scope.launch {
                  if (networkModel.checkConnection(context)) {
                    scope.launch {
                      val signInIntentSender = googleAuthUiClient.signIn()
                      @Suppress("LABEL_NAME_CLASH")
                      launcher.launch(
                        IntentSenderRequest.Builder(
                          signInIntentSender ?: return@launch
                        ).build()
                      )
                    }
                  }
                  else networkModel.toastMessage(context)
                }
              }
              .border(
                border = BorderStroke(
                  width = 1.2.dp,
                  color = mauve
                ),
                shape = RoundedCornerShape(50.dp)
              )
              .background(color = Color.Transparent),

          ) {
          Row(verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center) {
            AsyncImage(
              model = "https://img.icons8.com/?size=512&id=V5cGWnc9R4xj&format=png",
              contentDescription = "Google Logo",
              modifier = uiModel.googleLogoImageModifier(windowSize),
              alignment = Alignment.Center
            )
            Text(text = "Sign In With Google",
              modifier = Modifier.padding(
                start = 2.dp,
                end = uiModel.signInTextEndPaddingSmall(windowSize)),
              fontSize = uiModel.signInTextFontSizeSmall(windowSize),
              fontWeight = FontWeight.Medium,
              textAlign = TextAlign.Center)
          }
        }
      }
    }
  }

@Composable
fun AlternativeStartUpLogic(
  navController: NavHostController,
  productModel: ProductSearchViewModel,
  trendsModel: TrendsUIViewModel,
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
      trendsModel.accessTrends(trends, context)
      navController.navigate("trends_route")
    }
  }
}