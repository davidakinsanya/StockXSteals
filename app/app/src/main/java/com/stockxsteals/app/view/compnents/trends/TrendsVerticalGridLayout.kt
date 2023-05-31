package com.stockxsteals.app.view.compnents.trends

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.ui.GridItem
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.ui_coroutines.TrendCoroutineOnClick
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendsLazyGrid(trends: List<Trend>,
                  trendsModel: TrendsUIViewModel,
                  productModel: ProductSearchViewModel,
                  navController: NavHostController,
) {

  val items = (1..trends.size).map {
    GridItem(height = Random.nextInt(200, 300).dp,
      color = Color(0xFFFFFFFF).copy(1f))
  }

  LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalItemSpacing = 5.dp) {


    if (trends.isEmpty()) {
      val items2 = (1 .. 10).map {
       GridItem(height = Random.nextInt(200,300).dp,
          color = Color(0xFFFFFFFF).copy(1f))
      }

      items(items2.size) { num ->
        AlternateBox(item = items2[num])
      }

    } else {
      itemsIndexed(trends) { num, trend ->
        RandomColorBox(items[num], trend, trendsModel, productModel, navController)
      }
    }
  }
}

@Composable
fun AlternateBox(item: GridItem) {
  Box(modifier = Modifier
    .fillMaxWidth()
    .height(item.height)
    .clip(RoundedCornerShape(10.dp))
    .background(item.color)) {
  }
}

@Composable
fun RandomColorBox(item: GridItem,
                   trend: Trend,
                   trendsModel: TrendsUIViewModel,
                   productModel: ProductSearchViewModel,
                   navController: NavHostController) {

  val networkModel = trendsModel.getNetworkModel()
  val noQuota = trendsModel.getSearchModel().quota.collectAsState(initial = emptyList()).value.isEmpty()
  val quota = if (!noQuota) trendsModel.getSearchModel().quota.collectAsState(initial = emptyList()).value[0] else null
  val context = LocalContext.current
  val displayItem = remember { mutableStateOf(false) }
  val clicked = remember { mutableStateOf(false) }

  Box(modifier = Modifier
    .fillMaxWidth()
    .height(item.height)
    .clip(RoundedCornerShape(10.dp))
    .background(item.color)) {

      Row(modifier =
      Modifier
        .height(50.dp)
        .padding(end = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        AsyncImage(
          model = "https://img.icons8.com/?size=512&id=69155&format=png",
          contentDescription = "Expand Button",
          modifier =
          Modifier
            .fillMaxHeight(.9f)
            .padding(start = 150.dp)
            .fillMaxWidth(1f)
            .clickable {
              clicked.value = true
              productModel.addTrend(trend)
              navController.navigate(AppScreens.Search.route)
            })

      }

      Column(modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
          model = trend.image,
          contentDescription = "Sneaker Image",
          modifier =
          Modifier
            .padding()
            .fillMaxHeight(0.65f)
            .fillMaxWidth(0.65f))

        Text(text = trend.name,
          fontSize = 12.sp,
          maxLines = 2,
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          modifier =
          Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .width(200.dp)
           )
      }

    if (clicked.value) {
      TrendCoroutineOnClick(
        trendsModel = trendsModel,
        networkModel = networkModel,
        productModel = productModel,
        navController = navController,
        context = context,
        noQuota = noQuota,
        trend = trend,
        quota = quota,
        displayItem = displayItem
      )
    }
  }
}