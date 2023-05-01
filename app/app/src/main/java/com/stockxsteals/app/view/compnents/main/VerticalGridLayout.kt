package com.stockxsteals.app.view.compnents.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.model.ui.GridItem
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGrid() {
  val items = (1 .. 10).map {
    GridItem(height = Random.nextInt(200,300).dp,
      color = Color(0xFFFFFFFF).copy(1f))
  }

  LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalItemSpacing = 5.dp) {

    items(items) { item ->
      RandomColorBox(item = item)
    }
  }
}

@Composable
fun RandomColorBox(item: GridItem) {
  Box(modifier = Modifier
    .fillMaxWidth()
    .height(item.height)
    .clip(RoundedCornerShape(10.dp))
    .background(item.color)) {
  }
}