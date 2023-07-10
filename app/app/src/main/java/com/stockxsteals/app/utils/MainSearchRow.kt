package com.stockxsteals.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun MainSearchRow(text1: String,
                  text2: String,
                  paddingList: List<Dp>,
                  fontSize: TextUnit,
                  fontWeight: FontWeight,
) {
  Row(horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
      .padding(
    top = paddingList[0],
    bottom = paddingList[1],
    start = paddingList[2],
    end = paddingList[3])) {

    Text(
      text = text1,
      fontSize = fontSize,
      fontWeight = fontWeight
    )

    Text(
      text = text2,
      fontSize = fontSize,
      fontWeight = fontWeight
    )
  }
}