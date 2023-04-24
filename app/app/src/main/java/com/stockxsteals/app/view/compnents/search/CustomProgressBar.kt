package com.stockxsteals.app.view.compnents.search

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
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(progressNum: Int) {
  var progress by remember { mutableStateOf(0f) }
  val mauve = Color(224, 176, 255)
  val orchid = Color(153, 50, 204)

  /* to avoid the direct calculation of progress variable which is a Float
   and it can sometimes cause problems like it shows 0.4 to 0.400004 so, here I have use
   progressCount and we will increase and decrease it and then convert it to progress(Float)
   and then use that progress with our ProgressBar Width*/
  when (progressNum) {
    0 -> progress = 0.0f
    1 -> progress = 0.3f
    2 -> progress = 0.6f
    3 -> progress = 0.9f
    4 -> progress = 1.0f
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
      .padding(top = 150.dp, start = 15.dp, end = 30.dp)
  ) {
    // for the text above the progressBar
    Row(
      modifier = Modifier
        .widthIn(min = 30.dp)
        .fillMaxWidth(size),
      horizontalArrangement = Arrangement.End
    ) {
      // Text(text = "$progress")
    }

    // Progress Bar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(17.dp)
    ) {
      // for the background of the ProgressBar
      Box(
        modifier = Modifier
          .fillMaxSize()
          .clip(RoundedCornerShape(9.dp))
          .background(mauve)
      )
      // for the progress of the ProgressBar
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