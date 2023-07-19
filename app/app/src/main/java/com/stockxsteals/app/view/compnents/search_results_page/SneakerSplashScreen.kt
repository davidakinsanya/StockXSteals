package com.stockxsteals.app.view.compnents.search_results_page

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.ui_coroutines.DeleteSearchCoroutine
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SneakerSplashScreen(navController: NavHostController,
                        productModel: ProductSearchViewModel,
                        networkModel: NetworkViewModel,
                        windowSize: WindowSize
) {

  val focusManager = LocalFocusManager.current
  val context = LocalContext.current
  val searchRes = productModel.getFilterModel().bootMap.collectAsState()
  val uiModel = productModel.getUIModel()
  val map = searchRes.value
  val deleteSearch = remember { mutableStateOf(false) }

  val searchQuotaList = productModel
    .getSearchModel()
    .quota
    .collectAsState(initial = emptyList())
    .value

  var isPremium by remember { mutableStateOf(0) }

  LaunchedEffect(true) {
    isPremium = productModel.getPremiumModel().getIsPremium(1)
    if (productModel.getHistoryModel().getSearchByStamp("0") == null)
      navController.navigate(navController.previousBackStackEntry?.destination?.route!!)

    if (map.isEmpty()) {
      Toast.makeText(
        context,
        "Apologies in advance for the long search times.",
        Toast.LENGTH_SHORT
      ).show()

      Toast.makeText(
        context,
        "We are working very hard to drastically shorten these times.",
        Toast.LENGTH_SHORT
      ).show()
    }
  }

  Scaffold {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = Color(250, 240, 250))
        .padding(top = 30.dp)
    ) {
      Row(modifier =
      Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(start = 30.dp, end = uiModel.searchEntryEndPaddingLarge(windowSize)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(
          text = "Sneakers",
          fontWeight = FontWeight.ExtraBold,
          fontSize = uiModel.sneakersFontSizeSmall(windowSize),
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            deleteSearch.value = true

            if (navController.previousBackStackEntry?.destination?.route == AppScreens.Premium.route)
              navController.navigate(AppScreens.TopSearch.route)
            else
              navController.navigate(navController.previousBackStackEntry?.destination?.route!!)

            focusManager.clearFocus()
          }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon"
          )
        }
      }
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()
          .padding(top = 25.dp),
          contentPadding = PaddingValues(10.dp)) {

        items(1) {
          if (map.keys.isEmpty()) {
            AlternativeEntry(uiModel, windowSize)
          } else {
            map.keys.forEach {
              if (searchQuotaList.isNotEmpty())
                SearchEntry(
                  title = it,
                  result = map[it]!!,
                  productModel = productModel,
                  windowSize = windowSize,
                  networkModel = networkModel,
                  navController = navController,
                  searchQuota = searchQuotaList[0],
                  premiumQuota = isPremium
                )
            }
          }
          DeleteSearchCoroutine(deleteSearch = deleteSearch,
                                productModel = productModel)
        }
      }
    }
  }
}