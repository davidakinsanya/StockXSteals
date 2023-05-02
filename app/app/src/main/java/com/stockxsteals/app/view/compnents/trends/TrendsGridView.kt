package com.stockxsteals.app.view.compnents.trends

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.view.compnents.main.LazyGrid
import com.stockxsteals.app.viewmodel.ServerViewModel

@Composable
fun TrendsViewComponent(serverModel: ServerViewModel) {
  val currentTrends = serverModel.bootTrends.collectAsState()
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
      currentTrends.value.forEach {
        Log.d("trends!!", it.toString())
      }
      LazyGrid()
    }
  }
}