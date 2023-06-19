package com.stockxsteals.app.view.compnents.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel
import db.entity.DailySearchHistory

@Composable
fun Searches(settingModel: SettingViewModel,
             uiModel: UIViewModel,
             windowSize: WindowSize
) {
  val model = settingModel.getHistoryModel()
  val searches = model.searches.collectAsState(initial = emptyList()).value

  LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 30.dp)) {
    if (searches.isNotEmpty()) {
      items(searches.size) { i ->
        SearchRow(searches[i], uiModel, windowSize)
      }
    }
  }
}

@Composable
fun SearchRow(entry: DailySearchHistory,
              uiModel: UIViewModel,
              windowSize: WindowSize) {
  Row( modifier =
  Modifier
    .height(140.dp)
    .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround) {
    Text(text = entry.name,
      fontSize = 15.sp,
      textAlign = TextAlign.Left,
      modifier = Modifier
        .width(uiModel.searchEntryTextWidthSmall(windowSize)))

    AsyncImage(
      model = entry.image,
      contentDescription = "Sneaker Image",
      modifier = Modifier
        .size(150.dp)
        .padding(16.dp)
    )
  }
}