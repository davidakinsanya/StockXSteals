package com.stockxsteals.app.view.compnents.sneakers

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stockxsteals.app.R
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchEntry(title: String,
                result: List<String>,
                model: FilterViewModel,
                navController: NavHostController
) {

  val productModel = ProductSearchViewModel()
  val coroutineScope = rememberCoroutineScope()
  val searchRoute = AppScreens.Search.route

  Column(
    modifier = Modifier
      .fillMaxWidth(1.0f)
      .padding(5.dp)
      .height(140.dp)
      .clip(RoundedCornerShape(20.dp))
      .background(color = Color.White)
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .clickable {
          coroutineScope.launch {
            productModel.getProduct(
              result[0],
              model.getCurrentSearch().currency,
              model.getCurrentSearch().country)
          }
          navController.currentBackStackEntry?.savedStateHandle?.set(
            key = "productModel",
            value = productModel
          )
          navController.navigate(searchRoute)
        },
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

@Composable
fun AlternativeEntry() {
  for (i in 0..18)
    Column(
      modifier = Modifier
        .fillMaxWidth(1.0f)
        .padding(5.dp)
        .height(140.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(color = Color.White)
    ) {
      Row(modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column() {
          Box(
            modifier = Modifier
              .width(100.dp)
              .height(20.dp)
              .shimmerEffect()
          )
          Spacer(modifier = Modifier.height(16.dp))
          Box(
            modifier = Modifier
              .width(145.dp)
              .height(20.dp)
              .shimmerEffect()
          )
          Spacer(modifier = Modifier.height(16.dp))
          Box(
            modifier = Modifier
              .width(200.dp)
              .height(20.dp)
              .shimmerEffect()
          )
        }
        Spacer(Modifier.padding(15.dp))
        Box(
          modifier = Modifier
            .size(100.dp)
            .clip(RectangleShape)
            .shimmerEffect()
        )
      }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
  var size by remember {
    mutableStateOf(IntSize.Zero)
  }
  val transition = rememberInfiniteTransition()
  val startOffsetX by transition.animateFloat(
    initialValue = -2 * size.width.toFloat(),
    targetValue = 2 * size.width.toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(1000)
    )
  )

  background(
    brush = Brush.linearGradient(
      colors = listOf(
        Color(224, 176, 255),
        Color(218, 112, 214),
        Color(216, 191, 216),
      ),
      start = Offset(startOffsetX, 0f),
      end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
    )
  )
    .onGloballyPositioned {
      size = it.size
    }
}