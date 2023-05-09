package com.stockxsteals.app.view.compnents.searchpage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.stockxsteals.app.model.ui.ProductView
import com.stockxsteals.app.view.compnents.sneakers.shimmerEffect
import com.stockxsteals.app.viewmodel.ProductSearchViewModel

@Composable
fun SneakerViewComponent(productModel: ProductSearchViewModel?) {

  val productResults = productModel?.searchResult?.collectAsState()
  val productView = if (productResults != null) ProductView(productResults.value) else null

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(229, 255, 229))
  ) {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
        .fillMaxHeight(.88f)
    ) {
      if (productResults != null) {
        Pager(productView)
      } else {
        Pager(null)
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(view: ProductView?) {
  val pagerState = rememberPagerState()

  HorizontalPager(pageCount = 4) { page ->
    Card(
      Modifier
        .graphicsLayer {
          // Calculate the absolute offset for the current page from the
          // scroll position. We use the absolute value which allows us to mirror
          // any effects for both directions
          val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

          // We animate the scaleX + scaleY, between 85% and 100%
          lerp(
            start = 0.85f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
          ).also { scale ->
            scaleX = scale
            scaleY = scale
          }

          // We animate the alpha, between 50% and 100%
          alpha = lerp(
            start = 0.5f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
          )
        }
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(PaddingValues(30.dp))
        .clip(RoundedCornerShape(10.dp))
        .background(color = Color(0xFFFFFFFF).copy(1f))
    ) {
      if (view != null) {
        PagerTopRow(view.getConstant())
      }
    }
  }
}

@Composable
fun PagerTopRow(constants: List<String>) {

  Row(modifier = Modifier.padding(20.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {
    Column() {
      Text(
        text = constants[0],
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .width(200.dp)
          .height(20.dp)
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = constants[1],
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .width(145.dp)
          .height(20.dp)
      )
    }
    Spacer(Modifier.padding(5.dp))
    AsyncImage(
      model = constants[2],
      contentDescription = "Sneaker Image",
      modifier = Modifier
        .size(100.dp)
        .clip(RectangleShape))
  }

}