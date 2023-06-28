package com.stockxsteals.app.view.compnents.premium

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stockxsteals.app.R
import com.stockxsteals.app.model.ui.PremiumSellingPoint

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PremiumSplashScreen() {

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(top = 10.dp)
    .background(color = Color(250, 240, 250))) {
      Column(modifier =
      Modifier
        .fillMaxSize()
        .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
          PremiumTopRow()
          MainBody()
          UpgradeButton()
      }
  }
}

@Composable
fun PremiumTopRow() {
  Column(modifier =
  Modifier
    .fillMaxWidth()
    .padding(bottom = 0.dp)
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
fun MainBody() {
  val list = listOf(
    PremiumSellingPoint.Searches,
    PremiumSellingPoint.Features,
    PremiumSellingPoint.Community
  )

  LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
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
          modifier = Modifier.height(150.dp).verticalScroll(state = rememberScrollState())
        )
    }
  }
}

@Composable
fun UpgradeButton() {
  Column(Modifier
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
      Text(text = "Upgrade",
           fontSize = 12.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
             .align(Alignment.CenterVertically)
             .padding(16.dp))
    }

    Text(text = "Back",
         fontSize = 12.sp,
         fontWeight = FontWeight.Medium,
         textDecoration = TextDecoration.Underline,
         modifier =
         Modifier
           .padding(top = 10.dp)
           .clickable {

           }
         )
  }
}

/*
  uiModel: UIViewModel,
  trendsModel: TrendsUIViewModel?,
  productModel: ProductSearchViewModel?,
  navController: NavHostController,
  windowSize: WindowSize
 */