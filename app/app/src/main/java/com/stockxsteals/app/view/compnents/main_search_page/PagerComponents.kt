package com.stockxsteals.app.view.compnents.main_search_page

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
import com.stockxsteals.app.model.dto.Traits

@Composable
fun PagerTopRow(constants: List<String>) {
  Column(
    modifier = Modifier
      .fillMaxWidth(1.0f)
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
          fontSize = 12.sp,
          fontWeight = FontWeight.Medium,
          modifier = Modifier
            .width(145.dp)
            .height(20.dp)
        )
      }
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
fun AdditionalPagerData(count: Int, data: Map<String, List<Any>>) {
  Column(modifier = Modifier
    .padding(top = 150.dp)
    .fillMaxSize()) {
    if (count == 0) {
      var traits: List<*>? = null
      val emptyTraits = data["2"]?.get(0) as List<*>
      var cwText: Traits? = null
      var rdText: Traits? = null

      if (emptyTraits.isNotEmpty()) {
        traits = emptyTraits
        cwText = traits[1] as Traits
        rdText = traits[3] as Traits

      }

      if (emptyTraits.isNotEmpty()) {
        Text(
          text = "${cwText?.name}: ${cwText?.value}",
          fontSize = 14.sp,
          fontWeight = FontWeight.Light,
          modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 30.dp, end = 10.dp)
        )

        Text(
          text = "${rdText?.name}: ${rdText?.value}",
          fontSize = 14.sp,
          fontWeight = FontWeight.Light,
          modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
        )
      }

      Text(
        text = data["1"]?.get(0).toString(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(30.dp)
      )

    } else {
      // bids and sales data
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