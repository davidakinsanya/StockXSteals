package com.stockxsteals.app.view.compnents.trends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.readCurrentTrends
import com.stockxsteals.app.viewmodel.TrendsViewModel
import java.io.File

@Composable
fun TrendsViewComponent() {
  var currentTrends: List<Trend>? = null;

  val dir = File(LocalContext.current.filesDir, "/obj")
  if (!dir.exists()) dir.mkdirs()

  if (dir.listFiles()?.size == 1) {
    dir.listFiles()?.forEach { file ->
      currentTrends = readCurrentTrends(file.name)
    }
  }

  if (currentTrends == null)
    currentTrends = TrendsViewModel(LocalContext.current).bootTrends.collectAsState().value

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

      TrendsLazyGrid(currentTrends!!)

    }
  }
}