package com.stockxsteals.app.view.compnents.sneakers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SneakerSplashScreen(navController: NavHostController,
                        productSearchViewModel: ProductSearchViewModel,
                        networkModel: NetworkViewModel) {

  val focusManager = LocalFocusManager.current
  val searchRes = productSearchViewModel.getFilterModel().bootMap.collectAsState()
  val map = searchRes.value

  Scaffold {
    Column(
      modifier = Modifier
        .background(color = Color(250, 240, 250))
        .padding(top = 30.dp)
        .fillMaxSize()
    ) {
      Row(modifier =
      Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {

        Text(
          text = "Sneakers",
          fontWeight = FontWeight.ExtraBold,
          fontSize = 25.sp,
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(30.dp))
        IconButton(
          onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set(
              key = "filterModel",
              value = productSearchViewModel.getFilterModel()
            )
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
            AlternativeEntry()
          } else {
            map.keys.forEach {
              SearchEntry(
                title = it,
                result = map[it]!!,
                productSearchViewModel = productSearchViewModel,
                networkModel = networkModel,
                navController = navController
              )
            }
          }
        }
      }
    }
  }
}