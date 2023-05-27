package com.stockxsteals.app.http

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.dto.blankProduct
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel


private val service = ApiService.create()
@Composable
fun doRequest(model: TrendsUIViewModel, type: String,
              currency: String, int: Int): List<Trend> {

  val data = produceState<List<Trend>?>(
    initialValue = emptyList(),
    producer = { value = service.getTrends(type, currency) }
  )

  LaunchedEffect(int) {
    when (int) {
      0 -> {
        model.getTrendsModel().setFirstTrend(getCurrentDate(), data.value.toString())
      }

      -1 -> {
        model.getTrendsModel().updateTrends(getCurrentDate(), data.value.toString(), 0)
      }
    }
  }
  return data.value!!
}

@Composable
fun doRequest(search: String): Map<String, List<String>> {

  val data = produceState<Map<String, List<String>>>(
    initialValue = emptyMap(),
    producer = { value = service.getSearch(search) }
  )
  return data.value
}

@Composable
fun doRequest(slug: String,
              currency: String,
              country: String): Product {

  val data = produceState(
    initialValue = blankProduct(),
    producer = { value = service.searchProduct(slug, currency, country) }
  )
  return data.value
}