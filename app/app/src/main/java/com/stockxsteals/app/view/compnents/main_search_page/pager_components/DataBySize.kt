package com.stockxsteals.app.view.compnents.main_search_page.pager_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.model.dto.Market
import com.stockxsteals.app.model.dto.Variants
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun DataBySize(productModel: ProductSearchViewModel,
               uiModel: UIViewModel,
               windowSize: WindowSize,
               data: Map<String, List<Any>>,
               type: String,
               size: Double) {

  val variants = data["3"]?.get(0) as List<*>
  val currentSearch = productModel.getFilterModel().getCurrentSearch()
  var currency = ""

  Currency.values().forEach {
    if (it.type == currentSearch.currency) currency = it.symbol
  }
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
   // TODO: Same as Overall.

    Text(text = "Market Data (Buyers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3]))

    Text(
      text = "Lowest Asking Price: ${ if (market!!.bids.lowest_ask == null) "N/A" else "~$currency" + market!!.bids.lowest_ask }",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Highest Bidding Price: ${ if (market!!.bids.highest_bid == null) "N/A" else "~$currency" + market!!.bids.highest_bid }",
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
      text = "Last Sale: ${ "~$currency" + market!!.sales.last_sale }",
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