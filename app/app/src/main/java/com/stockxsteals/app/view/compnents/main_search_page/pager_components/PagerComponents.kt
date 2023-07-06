package com.stockxsteals.app.view.compnents.main_search_page.pager_components

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
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun PagerTopRow(constants: List<String>,
                uiModel: UIViewModel,
                windowSize: WindowSize) {
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
          fontSize = uiModel.pagerTopRowFontSize(windowSize),
          maxLines = 4,
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .width(uiModel.pagerTopRowTextWidth(windowSize))
            .padding(bottom = 10.dp)
        )
        Text(
          text = constants[1],
          fontSize = uiModel.pagerTopRowFontSizeTwo(windowSize),
          fontWeight = FontWeight.Medium,
          modifier = Modifier
            .width(uiModel.pagerTopRowTextWidthTwo(windowSize))
        )
      }

      val placeholder = constants[2].contains("Placeholder")
      if (placeholder)
        Image(
          painter = painterResource(R.drawable.stockxsteals),
          contentDescription = "Actual Placeholder",
          modifier = Modifier
            .size(140.dp)
            .padding(16.dp)
        )
      else
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
fun AdditionalSearchPagerData(productModel: ProductSearchViewModel,
                              uiModel: UIViewModel,
                              windowSize: WindowSize,
                              count: Int,
                              data: Map<String, List<Any>>,
                              type: String,
                              size: Double,
                              prevPage: Int) {

  Column(modifier = Modifier
    .padding(top = uiModel.additionalPagerDataTopPadding(windowSize))
    .fillMaxSize()) {
    when (count) {
      0 -> {
        DescriptionAndTraits(
          data = data,
          uiModel = uiModel,
          windowSize = windowSize
        )
      }
      1 -> {
        DataBySize(
          productModel = productModel,
          uiModel = uiModel,
          windowSize = windowSize,
          data = data,
          type = type,
          size = size)
      }
      else -> {
        DataOverall(
          productModel = productModel,
          uiModel = uiModel,
          windowSize = windowSize,
          data = data,
          prevPage = prevPage)
      }
    }
  }
}

@Composable
fun AdditionalTrendsPagerData(productModel: ProductSearchViewModel,
                              uiModel: UIViewModel,
                              windowSize: WindowSize,
                              count: Int,
                              data: Map<String, List<Any>>,
                              prevPage: Int) {

  Column(modifier = Modifier
    .padding(top = uiModel.additionalPagerDataTopPadding(windowSize))
    .fillMaxSize()) {
    when (count) {
      0 -> {
        DescriptionAndTraits(
          data = data,
          uiModel = uiModel,
          windowSize = windowSize
        )
      }
      else -> {
        DataOverall(
          productModel = productModel,
          uiModel = uiModel,
          windowSize = windowSize,
          data = data,
          prevPage = prevPage)
      }
    }
  }
}

@Composable
fun DescriptionAndTraits(data: Map<String, List<Any>>,
                         uiModel: UIViewModel,
                         windowSize: WindowSize) {
  val traits: List<*>?
  val paddingList = uiModel.additionalPagerDataPaddingList(windowSize)
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
      fontSize = uiModel.additionalPagerDataSmallText(windowSize),
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "${rdText?.name}: ${rdText?.value}",
      fontSize = uiModel.additionalPagerDataSmallText(windowSize),
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )
  }

  Text(
    text = data["1"]?.get(0).toString(),
    fontSize = uiModel.additionalPagerDataSmallText(windowSize),
    fontWeight = FontWeight.Light,
    modifier = Modifier.padding(
      top = paddingList[0],
      bottom = paddingList[1],
      start = paddingList[2],
      end = paddingList[3])
  )
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