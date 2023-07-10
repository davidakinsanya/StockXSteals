package com.stockxsteals.app.view.compnents.main_search_page.pager_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.model.dto.Market
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.utils.MainSearchRow
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
  var pageNum = "(2/2)"

  Currency.values().forEach {
    if (it.type == currentSearch.currency) {
      if (prevPage == 1) {
        currency = it.symbol
        pageNum = "(3/3)"
      }
    }
  }

  if (market != null) {
    // Header
    MainSearchRow("Market Data (Buyers)", "", paddingList, headersFontSize, FontWeight.Normal)


    val lowestAskFigure =
      if (market.bids.lowest_ask == null) "N/A" else ("~$currency" + market.bids.lowest_ask)
    MainSearchRow(
      "Lowest Asking Price",
      lowestAskFigure,
      paddingList,
      infoFontSize,
      FontWeight.Light
    )

    val highestBidFigure =
      if (market.bids.highest_bid == null) "N/A" else ("~$currency" + market.bids.highest_bid)
    MainSearchRow(
      "Highest Bidding Price",
      highestBidFigure,
      paddingList,
      infoFontSize,
      FontWeight.Light
    )

    val numberOfSellersFigure = market.bids.num_asks ?: 0
    MainSearchRow(
      "Number of Sellers",
      numberOfSellersFigure.toString(),
      paddingList,
      infoFontSize,
      FontWeight.Light
    )

    val numberOfBiddersFigure = market.bids.num_bids ?: 0
    MainSearchRow(
      "Number of Bidders",
      numberOfBiddersFigure.toString(),
      paddingList,
      infoFontSize,
      FontWeight.Light
    )

    // Header
    MainSearchRow("Market Data (Sellers)", "", paddingList, headersFontSize, FontWeight.Normal)


    val lastSaleFigure = "~$currency" + market.sales.last_sale
    MainSearchRow("Last Sale", lastSaleFigure, paddingList, infoFontSize, FontWeight.Light)

    val lastSaleThreeDaysFigure = (market.sales.last_sale_72h)
    MainSearchRow(
      "All Sales (Past 3 Days)",
      lastSaleThreeDaysFigure.toString(),
      paddingList,
      infoFontSize,
      FontWeight.Light
    )

    // Disclaimer
    MainSearchRow(
      "* Market data for all sizes. ",
      pageNum,
      paddingListDisclaimer,
      8.sp,
      FontWeight.Normal
    )
  }
}
