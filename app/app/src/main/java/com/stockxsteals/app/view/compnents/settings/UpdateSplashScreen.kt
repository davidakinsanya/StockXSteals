package com.stockxsteals.app.view.compnents.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.model.ui.allUpdates
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun UpdateScreen(uiModel: UIViewModel,
                 windowSize: WindowSize) {

  val updatesList = allUpdates()
  LazyColumn(modifier =
  Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.9f)
    .padding(start = uiModel.updateScreenStartPadding(windowSize)),
  ) {
    items(updatesList.size) { item ->
      Row (modifier =
      Modifier
        .padding(16.dp)
        .height(100.dp)) {

        Text(
          text = updatesList[item].date,
          fontSize = 30.sp,
          fontWeight = FontWeight.W600,
          modifier =
          Modifier
            .padding(bottom = uiModel.updateScreenDateBottomPadding(windowSize))
            .align(Alignment.CenterVertically)
        )

        Column(horizontalAlignment = Alignment.Start,
          verticalArrangement = Arrangement.Center,
          modifier =
          Modifier
            .fillMaxWidth()
            .padding(start = 35.dp,
                     end = uiModel.updateScreenEndPadding(windowSize))) {

          Text(
            text = updatesList[item].title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 5.dp)
          )

          Text(
            text = updatesList[item].description,
            fontSize = 12.sp,
            modifier = Modifier
              .height(150.dp)
              .verticalScroll(state = rememberScrollState())
          )
        }
      }
    }
  }
}