package com.stockxsteals.app.view.compnents.trends

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.ui.GridItem
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendsLazyGrid(trends: List<Trend>,
                  trendsModel: TrendsViewModel,
                  networkModel: NetworkViewModel
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
        RandomColorBox(items[num], trend, trendsModel, networkModel)
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
                   trendsModel: TrendsViewModel,
                   networkModel: NetworkViewModel) {

  val scope = rememberCoroutineScope()
  val noQuota = trendsModel.getSearchModel().quota.collectAsState(initial = emptyList()).value.isEmpty()
  val quota = if (!noQuota) trendsModel.getSearchModel().quota.collectAsState(initial = emptyList()).value[0] else null
  val context = LocalContext.current



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
              var displayItem = false

              scope.launch {
                val isPremium = trendsModel.getPremiumModel().getIsPremium() == 1
                if (networkModel.checkConnection(context)) {
                  if (noQuota) {
                    trendsModel.getSearchModel().insertSearch()
                    trendsModel.getHistoryModel()
                      .addSearch(getCurrentDate(), trend.image, trend.name, "")
                    displayItem = true

                  } else if (trendsModel.getSearchModel().dbLogic(quota!!) == 1 || isPremium) {
                    trendsModel.getHistoryModel()
                      .addSearch(getCurrentDate(), trend.image, trend.name, "")
                    if (!isPremium) {
                      Toast
                        .makeText(
                          context,
                          "${quota.search_limit - quota.search_number} free daily searches left.",
                          Toast.LENGTH_LONG
                        )
                        .show()
                    }

                    displayItem = true

                  } else {
                    Toast
                      .makeText(context, "Please upgrade to L8test+.", Toast.LENGTH_LONG)
                      .show()
                  }
                  if (displayItem) { }

                } else {
                  networkModel.toastMessage(context)
                }
              }
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

  }
}