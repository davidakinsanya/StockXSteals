package com.stockxsteals.app.http

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.blankProduct

private val service = ApiService.create()


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