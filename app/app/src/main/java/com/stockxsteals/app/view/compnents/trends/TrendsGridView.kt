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
import com.stockxsteals.app.viewmodel.ui.TrendsViewModel
import java.io.File

@Composable
fun TrendsViewComponent(trendsModel: TrendsViewModel) {
  val currentTrends: List<Trend>?
  val context = LocalContext.current

  val dir = File(context.filesDir, "/trends/obj")
  if (!dir.exists()) dir.mkdirs()
  val listSize = dir.listFiles()?.size

  currentTrends = when (listSize) {
    0 -> {
      trendsModel.bootTrends.collectAsState().value
    }
    1 -> {
      readCurrentTrends(dir.listFiles()?.get(0)!!.path) // TODO: Replace with DB operation
    }
    else -> {
      listOf()
    }
  }

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

      TrendsLazyGrid(currentTrends!!, trendsModel)

    }
  }
}