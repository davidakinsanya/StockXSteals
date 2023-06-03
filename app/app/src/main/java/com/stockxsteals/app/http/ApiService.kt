package com.stockxsteals.app.http

import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface ApiService {

  suspend fun getSearch(search: String): Map<String, List<String>>

  suspend fun getTrends(query: String, currency: String): List<Trend>

  suspend fun searchProduct(query: String, currency: String, country: String): Product

  companion object {
    fun create(): ApiService {
      return ApiServiceImpl(
        client = HttpClient(Android) {
          install(Logging) {
            level = LogLevel.ALL
          }
          install(JsonFeature) {
            serializer = KotlinxSerializer()
          }
        })
    }
  }
}