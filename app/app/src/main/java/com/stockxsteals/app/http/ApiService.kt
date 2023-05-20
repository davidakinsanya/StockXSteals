package com.stockxsteals.app.http

import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend

interface ApiService {

  suspend fun getSearch(search: String): Map<String, List<String>>

  suspend fun  getTrends(query: String, currency: String): List<Trend>

  suspend fun searchProduct(query: String, currency: String, country: String): Product

}