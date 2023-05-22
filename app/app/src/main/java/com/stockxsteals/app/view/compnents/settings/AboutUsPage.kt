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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.R

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
    Text(
      text = "Welcome to L8test. Think of a mobile-first, the digital \"Apple Store\" solely for sneakers.\n" +
              "At L8test, we take a minimalist approach in presenting you with the latest, most \n" +
              "market-favored sneakers in real-time.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left
    )

    Text(
      text = "We compartmentalize our business approach in two distinct parts;\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Center
    )

    Text(
      text = "A term which depicts taking a real deep dive, combing through real-time marketplace data \n" +
              "and relaying that data back to our user base in a clean, concise manner as well as \n" +
              "providing (hopefully) an increasingly more accurate depiction of any given sneakers \n" +
              "value to the global consumer at any given time, whether that be someone interesting \n" +
              "in buying or selling a pair of sneakers.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left
    )

    Text(
      text = "We matter because we provide an innovative and contemporary approach to the growing\n" +
              "digital sneaker marketplace on a global scale. We are lean and willing are willing receptors \n" +
              "to change and growth.\n",
      fontSize = 16.sp,
      modifier = Modifier.width(450.dp),
      textAlign = TextAlign.Left
    )

    Text(
      text = "Signed, L8test.",
      fontSize = 16.sp,
      modifier = Modifier
        .padding(bottom = 40.dp)
        .width(450.dp),
      textAlign = TextAlign.Center
    )
  }
}