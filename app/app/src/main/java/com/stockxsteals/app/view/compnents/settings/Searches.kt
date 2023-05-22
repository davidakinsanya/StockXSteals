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
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import db.entity.DailySearchHistory

@Composable
fun Searches(settingModel: SettingViewModel) {
  val searches = settingModel.getHistoryModel().searches.collectAsState(initial = emptyList()).value
  LazyColumn(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
    items(searches.size) { i ->
      SearchRow(searches[i])
    }
  }
}

@Composable
fun SearchRow(entry: DailySearchHistory) {
  Row( modifier =
  Modifier
    .padding(5.dp)
    .height(140.dp)
    .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround) {
    Text(text = entry.name,
      fontSize = 15.sp,
      textAlign = TextAlign.Left,
      modifier = Modifier
        .width(200.dp)
        .padding(16.dp))

    AsyncImage(
      model = entry.image,
      contentDescription = "Sneaker Image",
      modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp)
        .fillMaxWidth(0.7f)
    )
  }
}