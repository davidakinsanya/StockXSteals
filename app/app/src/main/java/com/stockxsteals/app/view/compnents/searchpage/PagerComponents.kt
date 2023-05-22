package com.stockxsteals.app.view.compnents.searchpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stockxsteals.app.R

@Composable
fun PagerTopRow(constants: List<String>) {
  Column(
    modifier = Modifier
      .fillMaxWidth(1.0f)
      .padding(0.dp)
      .height(140.dp)
      .clip(RoundedCornerShape(5.dp))
      .background(color = Color.White),
  ) {
    Row(
      modifier = Modifier.padding(20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        Text(
          text = constants[0],
          fontSize = 15.sp,
          maxLines = 4,
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .width(150.dp)
            .height(70.dp)
        )
        Text(
          text = constants[1],
          fontSize = 8.sp,
          fontWeight = FontWeight.Medium,
          modifier = Modifier
            .width(145.dp)
            .height(20.dp)
        )
      }
      Spacer(modifier = Modifier.width(30.dp))
      AsyncImage(
        model = constants[2],
        contentDescription = "Sneaker Image",
        modifier = Modifier
          .fillMaxWidth(),
        alignment = Alignment.Center
      )
    }
  }
}

@Composable
fun SinglePagerComponent() {
  Column(modifier = Modifier.fillMaxHeight(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center) {

    Image(painter = painterResource(R.drawable.stockxsteals),
      contentDescription = "Placeholder",
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.35f)
        .graphicsLayer { alpha = 0.55f })

    Text(text = "Hit the \uD83D\uDD0D icon above to start your search.",
         modifier = Modifier.width(250.dp),
         maxLines = 2,
      textAlign = TextAlign.Center,
      fontSize = 20.sp,
      fontWeight = FontWeight.Light)
  }
}