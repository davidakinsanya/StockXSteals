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
import com.stockxsteals.app.model.dto.Market
import com.stockxsteals.app.model.dto.Traits
import com.stockxsteals.app.model.dto.Variants
import com.stockxsteals.app.utils.WindowSize
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
            .height(uiModel.pagerTopRowHeight(windowSize))
        )
        Text(
          text = constants[1],
          fontSize = uiModel.pagerTopRowFontSizeTwo(windowSize),
          fontWeight = FontWeight.Medium,
          modifier = Modifier
            .width(uiModel.pagerTopRowTextWidthTwo(windowSize))
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
fun AdditionalPagerData(uiModel: UIViewModel,
                        windowSize: WindowSize,
                        count: Int,
                        data: Map<String, List<Any>>,
                        type: String,
                        size: Double) {

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
          uiModel = uiModel,
          windowSize = windowSize,
          data = data,
          type = type,
          size = size)
      }
      else -> {
        DataOverall(
          uiModel = uiModel,
          windowSize = windowSize,
          data = data)
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
fun DataBySize(uiModel: UIViewModel,
               windowSize: WindowSize,
               data: Map<String, List<Any>>,
               type: String,
               size: Double) {

  val variants = data["3"]?.get(0) as List<*>
  val paddingList = uiModel.additionalPagerDataPaddingList(windowSize)
  val paddingListDisclaimer = uiModel.additionalPagerDataPaddingListDisclaimer(windowSize)
  val infoFontSize = uiModel.additionalPagerDataInfoFontSize(windowSize)
  val headersFontSize = uiModel.additionalPagerDataHeadersFontSize(windowSize)
  var market: Market? = null

  variants.forEach { variant ->
    variant as Variants
    variant.sizes.forEach { vSize ->

      if (vSize.size.contains(size.toString().replace(".0", ""))
        && vSize.type ==
        type.replace("_", " ").lowercase())

        market = variant.market
    }
  }

  if (market != null) {

    Text(text = "Market Data (Buyers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3]))

    Text(
      text = "Lowest Asking Price: ${ if (market!!.bids.lowest_ask == null) "N/A" else market!!.bids.lowest_ask }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Highest Bidding Price: ${ if (market!!.bids.highest_bid == null) "N/A" else market!!.bids.highest_bid }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Number of Sellers: ${ if (market!!.bids.num_asks == null) 0 else market!!.bids.num_asks }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Number of Bidders: ${ if (market!!.bids.num_bids == null) 0 else market!!.bids.num_bids }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(text = "Market Data (Sellers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Last Sale: ${market!!.sales.last_sale }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "All Sales (Past 3 Days): ${market!!.sales.last_sale_72h }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text("* Market data for ${type.replace("_", " ")} Size ${size.toString().replace(".0", "")} only.",
      fontSize = 8.sp,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingListDisclaimer[0],
        bottom = paddingListDisclaimer[1],
        start = paddingListDisclaimer[2],
        end = paddingListDisclaimer[3]))

  }
}

@Composable
fun DataOverall(uiModel: UIViewModel,
                windowSize: WindowSize,
                data: Map<String, List<Any>>) {

  val market = data["4"]?.get(0) as Market?
  val paddingList = uiModel.additionalPagerDataPaddingList(windowSize)
  val paddingListDisclaimer = uiModel.additionalPagerDataPaddingListDisclaimer(windowSize)
  val infoFontSize = uiModel.additionalPagerDataInfoFontSize(windowSize)
  val headersFontSize = uiModel.additionalPagerDataHeadersFontSize(windowSize)
  if (market != null) {
    
    Text(text = "Market Data (Buyers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3]))

    Text(
      text = "Lowest Asking Price: ${market.bids.lowest_ask ?: "N/A"}",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Highest Bidding Price: ${market.bids.highest_bid ?: "N/A"}",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Number of Sellers: ${market.bids.num_asks ?: 0}",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Number of Bidders: ${market.bids.num_bids ?: 0}",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(text = "Market Data (Sellers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3]))

    Text(
      text = "Last Sale: ${market.sales.last_sale }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "All Sales (Past 3 Days): ${market.sales.last_sale_72h }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text("* Market data for all sizes.",
      fontSize = 8.sp,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingListDisclaimer[0],
        bottom = paddingListDisclaimer[1],
        start = paddingListDisclaimer[2],
        end = paddingListDisclaimer[3]))
    
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