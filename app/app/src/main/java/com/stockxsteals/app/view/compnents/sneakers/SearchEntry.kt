package com.stockxsteals.app.view.compnents.sneakers

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.stockxsteals.app.viewmodel.FilterViewModel

@Composable
fun SearchEntry(title: String,
                result: List<String>,
                model: FilterViewModel,
                navController: NavHostController
) {

  Card(
    modifier = Modifier
      .fillMaxWidth(1.0f)
      .padding(5.dp)
      .height(140.dp),
    elevation = 10.dp,
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

      AsyncImage(
        model = result.elementAt(1).toString(),
        contentDescription = title,
        modifier = Modifier
          .fillMaxHeight()
          .padding(16.dp)
          .fillMaxWidth(0.7f))
    }
  }

}