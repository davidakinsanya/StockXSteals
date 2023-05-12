package com.stockxsteals.app.view.compnents.searchpage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.stockxsteals.app.model.ui.ProductView
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun SneakerViewComponent(productModel: ProductSearchViewModel?, uiModel: UIViewModel) {

  val productResults = productModel?.searchResult?.collectAsState()
  val productView =
    if (uiModel.productIsNotNull(productResults))
      ProductView(productResults!!.value)
    else
      null

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
      if (uiModel.productIsNotNull(productResults)) {
        Pager(productView, uiModel.productIsNotNull(productResults))
      } else {
        Pager(null, uiModel.productIsNotNull(productResults))
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(view: ProductView?, bool: Boolean) {

  val pagerState = rememberPagerState()
  val count = if (bool)
    view!!.listForPager().keys.size
  else 1

  HorizontalPager(pageCount = count) { page ->
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
      if (bool) PagerTopRow(view!!.getConstant())
    }
  }
}