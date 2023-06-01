package com.stockxsteals.app.http

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.dto.blankProduct
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

private val service = ApiService.create()

@Composable
fun doRequest(model: TrendsUIViewModel, type: String,
              currency: String, int: Int): List<Trend> {

  var data = listOf<Trend>()

  LaunchedEffect(int) {
    data = service.getTrends(type, currency)
    when (int) {
      0 -> {
        model.getTrendsModel().setFirstTrend(getCurrentDate(), data.toString())
      }

      1 -> {
        model.getTrendsModel().updateTrends(getCurrentDate(), data.toString(), 0)
      }
    }
  }
  return data
}

@Composable
fun doRequest(search: String): Map<String, List<String>> {
  var data = mapOf<String, List<String>>()

  LaunchedEffect(key1 = true) {
    data = service.getSearch(search)
  }

  return data
}

@Composable
fun doRequest(slug: String,
              currency: String,
              country: String): Product {
  var data = blankProduct()

  LaunchedEffect(key1 = true) {
    data = service.searchProduct(slug, currency, country)
  }

  return data
}