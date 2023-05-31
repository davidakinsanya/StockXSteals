package com.stockxsteals.app.view.compnents.top_search

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomProgressBar(progressNum: Int) {
  var progress by remember { mutableStateOf(0f) }
  val mauve = Color(224, 176, 255)
  val orchid = Color(153, 50, 204)

  when (progressNum) {
    0 -> progress = 0.0f
    1 -> progress = 0.3f
    2 -> progress = 0.6f
    3 -> progress = 0.94f
  }

  val size by animateFloatAsState(
    targetValue = progress,
    tween(
      durationMillis = 1000,
      delayMillis = 200,
      easing = LinearOutSlowInEasing
    )
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 100.dp, start = 15.dp, end = 30.dp)
  ) {
    // for the text above the progressBar
    Row(
      modifier = Modifier
        .widthIn(min = 30.dp)
        .fillMaxWidth(fraction = size),
      horizontalArrangement = if (progressNum < 3) Arrangement.End else Arrangement.Center
    ) {
      if (progressNum < 3)
        Text(text = "$progressNum/3", fontSize = 12.sp, fontWeight = FontWeight.Bold)
      else
        Text("Enter Your Sneaker", fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(17.dp)
    ) {

      Box(
        modifier = Modifier
          .fillMaxSize()
          .clip(RoundedCornerShape(9.dp))
          .background(mauve)
      )

      Box(
        modifier = Modifier
          .fillMaxWidth(size)
          .fillMaxHeight()
          .clip(RoundedCornerShape(9.dp))
          .background(orchid)
          .animateContentSize()
      )
    }
  }
}