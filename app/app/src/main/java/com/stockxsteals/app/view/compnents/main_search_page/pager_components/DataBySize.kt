package com.stockxsteals.app.view.compnents.main_search_page.pager_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.model.dto.Market
import com.stockxsteals.app.model.dto.Variants
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.utils.MainSearchRow
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
    // Header
    MainSearchRow("Market Data (Buyers)", "", paddingList, headersFontSize, FontWeight.Normal)


    val lowestAskFigure = if (market!!.bids.lowest_ask == null) "N/A" else ("~$currency" + market!!.bids.lowest_ask)
    MainSearchRow("Lowest Asking Price", lowestAskFigure, paddingList, infoFontSize, FontWeight.Light)

    val highestBidFigure =  if (market!!.bids.highest_bid == null) "N/A" else ("~$currency" + market!!.bids.highest_bid)
    MainSearchRow("Highest Bidding Price", highestBidFigure, paddingList, infoFontSize, FontWeight.Light)

    val numberOfSellersFigure = market!!.bids.num_asks ?: 0
    MainSearchRow("Number of Sellers", numberOfSellersFigure.toString(), paddingList, infoFontSize, FontWeight.Light)

    val numberOfBiddersFigure = market!!.bids.num_bids ?: 0
    MainSearchRow("Number of Bidders", numberOfBiddersFigure.toString(), paddingList, infoFontSize, FontWeight.Light)

    // Header
    MainSearchRow("Market Data (Sellers)", "", paddingList, headersFontSize, FontWeight.Normal)


    val lastSaleFigure = "~$currency" + market!!.sales.last_sale
    MainSearchRow("Last Sale", lastSaleFigure, paddingList, infoFontSize, FontWeight.Light)

    val lastSaleThreeDaysFigure = (market!!.sales.last_sale_72h)
    MainSearchRow("All Sales (Past 3 Days)", lastSaleThreeDaysFigure.toString(), paddingList, infoFontSize, FontWeight.Light)

    // Disclaimer
    MainSearchRow("* Market data for ${type.replace("_", " ")} Size ${size.toString().replace(".0", "")} only."
                        , "(2/3)", paddingListDisclaimer, 8.sp, FontWeight.Normal)

  } else {
    SinglePagerComponent(text1 = "Sorry, we could not find any size-specific market data. Please keep swiping.")
  }
}