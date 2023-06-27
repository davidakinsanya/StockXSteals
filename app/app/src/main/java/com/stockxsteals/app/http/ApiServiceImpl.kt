package com.stockxsteals.app.http

import android.util.Log
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.dto.blankProduct
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ApiServiceImpl(private val client: HttpClient): ApiService {

  private val baseUrl = "https://stockx1.p.rapidapi.com/v2/stockx/"
  private val searchURL = "http://192.168.1.127:5000"
  private val apiHost = "stockx1.p.rapidapi.com"
  private val apiKey = "##########################################"

  override suspend fun getSearch(search: String): Map<String, List<String>> {
    return try {
      client.get(searchURL) {
        url {
          parameters.append("search", search)
        }
      }
    } catch (e: RedirectResponseException) {
      // 3xx - code response
      Log.d("3XX", e.response.status.description)
      mapOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      Log.d("4XX", e.response.status.description)
      mapOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      Log.d("5XX", e.response.status.description)
      mapOf()
    }
  }

  override suspend fun getTrends(query: String, currency: String): List<Trend> {
    return try {
      client.get(baseUrl + "trends") {
        url {
          parameters.append("query", query)
          parameters.append("currency", currency)
        }
        headers {
          append("X-RapidAPI-Key", apiKey)
          append("X-RapidAPI-Host", apiHost)
        }
      }
    } catch (e: RedirectResponseException) {
      // 3xx - code response
      Log.d("3XX", e.response.status.description)
      listOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      listOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      Log.d("5XX", e.response.status.description)
      listOf()
    }
  }

  override suspend fun searchProduct(
    query: String,
    currency: String,
    country: String
  ): Product {

    return try {
      client.get(baseUrl + "product") {
        url {
          parameters.append("query", query)
          if (currency.isNotEmpty())
            parameters.append("currency", currency)
          if (country.isNotEmpty())
            parameters.append("country", country)

        }
        headers {
          append("X-RapidAPI-Key", apiKey)
          append("X-RapidAPI-Host", apiHost)
        }
      }
    } catch (e: RedirectResponseException) {
      // 3xx - code response
      Log.d("3XX", e.response.status.description)
      blankProduct()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      Log.d("4XX", e.response.status.description)
      blankProduct()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      Log.d("5XX", e.response.status.description)
      blankProduct()
    }
  }
}