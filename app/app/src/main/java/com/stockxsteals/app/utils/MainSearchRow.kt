package com.stockxsteals.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit


@Composable
fun MainSearchRow(description: String,
                  figure: String,
                  paddingList: List<Dp>,
                  fontSize: TextUnit
) {
  Row(horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.padding(
    top = paddingList[0],
    bottom = paddingList[1],
    start = paddingList[2],
    end = paddingList[3])) {

    Text(
      text = description,
      fontSize = fontSize,
      fontWeight = FontWeight.Light
    )

    Text(
      text = figure,
      fontSize = fontSize,
      fontWeight = FontWeight.Light
    )
  }
}