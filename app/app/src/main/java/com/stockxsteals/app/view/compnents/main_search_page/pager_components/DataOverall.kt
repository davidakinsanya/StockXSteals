package com.stockxsteals.app.view.compnents.main_search_page.pager_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.model.dto.Market
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun DataOverall(productModel: ProductSearchViewModel,
                prevPage: Int,
                uiModel: UIViewModel,
                windowSize: WindowSize,
                data: Map<String, List<Any>>) {

  val market = data["4"]?.get(0) as Market?
  val paddingList = uiModel.additionalPagerDataPaddingList(windowSize)
  val paddingListDisclaimer = uiModel.additionalPagerDataPaddingListDisclaimer(windowSize)
  val infoFontSize = uiModel.additionalPagerDataInfoFontSize(windowSize)
  val headersFontSize = uiModel.additionalPagerDataHeadersFontSize(windowSize)

  val currentSearch = productModel.getFilterModel().getCurrentSearch()
  var currency = "$"

  Currency.values().forEach {
    if (it.type == currentSearch.currency) {
      if (prevPage == 1)
        currency = it.symbol
    }
  }

  if (market != null) {
    /*
      val lowestAskFigure = if (market.bids.lowest_ask == null) "N/A" else ("~$currency" + market.bids.lowest_ask)
      val highestBidFigure =  if (market.bids.highest_bid == null) "N/A" else ("~$currency" + market.bids.highest_bid)}
      val numberOfSellersFigure = market.bids.num_asks ?: 0
      val numberOfBiddersFigure = market.bids.num_bids ?: 0
      val lastSaleFigure = "~$currency" + market.sales.last_sale
      val lastSaleThreeDaysFigure = (market.sales.last_sale_72h).toString()
     */

      Text(text = "Market Data (Buyers)",
      fontSize = headersFontSize,
      fontWeight = FontWeight.Normal,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3]))

    Text(
      text = "Lowest Asking Price: ${if (market.bids.lowest_ask == null) "N/A" else ("~$currency" + market.bids.lowest_ask)}",
      fontSize = infoFontSize,
      fontWeight = FontWeight.Light,
      modifier = Modifier.padding(
        top = paddingList[0],
        bottom = paddingList[1],
        start = paddingList[2],
        end = paddingList[3])
    )

    Text(
      text = "Highest Bidding Price: ${ if (market.bids.highest_bid == null) "N/A" else ("~$currency" + market.bids.highest_bid)}",
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
      text = "Last Sale: ${"~$currency" + market.sales.last_sale }",
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
