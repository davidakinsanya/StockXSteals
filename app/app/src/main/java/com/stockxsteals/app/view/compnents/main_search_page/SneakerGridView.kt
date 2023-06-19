package com.stockxsteals.app.view.compnents.main_search_page

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
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.ui.ProductView
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel

@Composable
fun SneakerViewComponent(productModel: ProductSearchViewModel,
                         navController: NavHostController,
                         windowSize: WindowSize
) {

  val uiModel = productModel.getUIModel()
  val productResults = when (navController.previousBackStackEntry?.destination?.route) {
    "sneaker_search" -> {
      productModel.searchResult.collectAsState()
    }
    /*
    "trends" -> {
      productModel.trendSearch.collectAsState()
    } */
    else -> {
      null
    }
  }

  val prevPage: Int = when (navController.previousBackStackEntry?.destination?.route) {
    "sneaker_search" -> {
      1
    }
    /*
    "trends" -> {
      2
    } */
    else -> {
      -1
    }
  }

  if (navController.currentDestination?.route != AppScreens.Search.route) {
    productModel.clearProduct()
    productModel.clearTrend()
  }

  val productView =
    if (uiModel.productIsNotNull(productResults))
      ProductView(productResults!!.value, navController)
    else
      null

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(229, 255, 229))
  ) {
    Column(
      modifier = Modifier
        .padding(top = 10.dp, bottom = uiModel.searchGridViewSmallPadding(windowSize))
        .fillMaxHeight(.93f)
        .fillMaxWidth()
    ) {
      if (uiModel.productIsNotNull(productResults)) {
        Pager(productView, uiModel.productIsNotNull(productResults), productModel = productModel, prevPage = prevPage)
      } else {
        Pager(null, uiModel.productIsNotNull(productResults), productModel = productModel, prevPage = prevPage)
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(view: ProductView?, bool: Boolean, productModel: ProductSearchViewModel, prevPage: Int) {


  val currentSearch = productModel.getFilterModel().getCurrentSearch()

  val pagerState = rememberPagerState()
  val count: Int = when(prevPage) {
    1 -> {
      3
    }

    2 -> {
      1
    }
    else -> {
      1
    }
  }

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
      if (bool) {
        PagerTopRow(view!!.getConstant())
        AdditionalPagerData(
          page,
          view.listForPager(),
          currentSearch.sizeType,
          currentSearch.size)
      }
      else
        SinglePagerComponent()

    }
  }
}