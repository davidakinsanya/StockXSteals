package com.stockxsteals.app.view.compnents.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.R
import com.stockxsteals.app.utils.CustomText

@Composable
fun AboutUs() {
  LazyColumn() {
    item {
      TopRow()
      DisplayText()
    }
  }
}

@Composable
fun TopRow() {
  Row(
    modifier =
    Modifier
      .fillMaxWidth()
      .padding(bottom = 25.dp)
      .height(120.dp),
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(painter = painterResource(R.drawable.stockxsteals),
      contentDescription = "Logo",
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .graphicsLayer { alpha = 0.55f })
  }
}

@Composable
fun DisplayText() {
  Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {

    CustomText().AppendCustomText(
      text = "**Welcome to L8test.**",
      modifier = Modifier.width(450.dp),
      fontSize = 16.sp
    )

    Text(
      text = "\n\nThink of a mobile-first, the digital \"Apple Store\" solely for sneakers. " +
              "At L8test, we take a minimalist approach in presenting you with the latest, most " +
              "market-favored sneakers in real-time.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left,
      fontWeight = FontWeight.Light
    )

    Text(
      text = "We compartmentalize our business approach in two distinct parts;\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Light
    )

    Text(
      text = "Baseline sneaker trivia, regarding all sneakers, whether that is fresh on the market or " +
              "in circulation for a few years. And the second part, a new term dubbed by us as " +
              "\"Sneaker Analytics\".\n\nA term which depicts taking a real deep dive, combing through real-time marketplace data " +
              "and relaying that data back to our user base in a clean, concise manner as well as providing " +
              "an increasingly more accurate depiction of any given sneakers value " +
              "to the global consumer at any given time, whether that be someone interesting " +
              "in buying or selling a pair of sneakers.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left,
      fontWeight = FontWeight.Light
    )

    Text(
      text = "We matter because we provide an innovative and contemporary approach to the growing " +
              "digital sneaker marketplace on a global scale. We are lean and willing are willing receptors " +
              "to change and growth.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left,
      fontWeight = FontWeight.Light
    )

    CustomText().AppendCustomText(
      text = "**Signed, L8test.**",
      modifier = Modifier
        .padding(bottom = 40.dp)
        .width(450.dp),
      fontSize = 16.sp
    )
  }
}