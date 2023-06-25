package com.stockxsteals.app.view.compnents.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

  LazyColumn(modifier = Modifier.padding(end = 30.dp)) {
    if (searches.isNotEmpty()) {
      items(searches.size) { i ->
        SearchRow(searches[i], uiModel, windowSize)
      }
    }
  }
}

fun formatName(name: String): String {
  var returnedName = ""
  val nameList = name.split("-")

  nameList.forEach { it ->

    var string = it.replace(it,
      it.replaceFirstChar {
        if (it.isLowerCase() && it.isLetter())
          it.titlecase()
        else it.toString()
      })

    if (string.length == 2)
      string = string.uppercase()

    returnedName += "$string "
  }

  return returnedName
}

@Composable
fun SearchRow(entry: DailySearchHistory,
              uiModel: UIViewModel,
              windowSize: WindowSize) {

  Row(modifier =
  Modifier
    .height(140.dp)
    .padding(start = 30.dp,
      bottom = 50.dp)
    .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween) {
    Text(text = formatName(entry.name),
      fontSize = 15.sp,
      fontWeight = FontWeight.SemiBold,
      textAlign = TextAlign.Left,
      modifier = Modifier
        .width(uiModel.searchEntryTextWidthSmall(windowSize)))

    AsyncImage(
      model = entry.image,
      contentDescription = "Sneaker Image",
    )
  }
}