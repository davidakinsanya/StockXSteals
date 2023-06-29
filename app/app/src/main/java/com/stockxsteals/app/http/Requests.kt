package com.stockxsteals.app.http

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState

private val service = ApiService.create()

@Composable
fun doRequest(search: String): Map<String, List<String>> {
  val data = produceState<Map<String, List<String>>>(
    initialValue = emptyMap(),
    producer = { value = service.getSearch(search) }
  )
  return data.value
}