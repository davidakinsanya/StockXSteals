package com.stockxsteals.app.http

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.blankProduct

private val service = ApiService.create()

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
              country: String): List<Product> {
  val data = produceState(
    initialValue = listOf(blankProduct()),
    producer = { value = service.searchProduct(slug, currency, country) }
  )

  return data.value
}