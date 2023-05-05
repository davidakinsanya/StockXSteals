package com.stockxsteals.app.view.compnents.sneakers

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stockxsteals.app.R
import com.stockxsteals.app.viewmodel.FilterViewModel

@Composable
fun SearchEntry(title: String,
                result: List<String>,
                model: FilterViewModel,
                navController: NavHostController
) {
  val mauve = Color(224, 176, 255)

  Column(
    modifier = Modifier
      .fillMaxWidth(1.0f)
      .padding(5.dp)
      .height(140.dp)
      .border(
        border = BorderStroke(width = 0.5.dp,
          color = mauve),
        shape = RoundedCornerShape(20.dp))
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceAround
    ) {
      Text(text = title,
        fontSize = 15.sp,
        textAlign = TextAlign.Left,
        modifier = Modifier
          .width(200.dp)
          .padding(16.dp))


      val placeholder =  result[1].contains("Placeholder")

      if (!placeholder)
        AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(result[1])
            .crossfade(true)
            .build(),
          contentDescription = title,
          modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .fillMaxWidth(0.7f),
        placeholder = painterResource(R.drawable.stockxsteals))
      else
        Image(painter = painterResource(R.drawable.stockxsteals),
          contentDescription = "Actual Placeholder",
          modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight()
            .padding(16.dp))
    }
  }

}