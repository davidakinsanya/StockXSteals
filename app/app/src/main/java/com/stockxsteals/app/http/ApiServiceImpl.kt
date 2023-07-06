package com.stockxsteals.app.http

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.stockxsteals.app.BuildConfig
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.dto.blankProduct
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import db.entity.DailySearchQuota
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiServiceImpl(private val client: HttpClient): ApiService {

  private val baseUrl = BuildConfig.STOCKX_BASE_URL
  private val searchURL = BuildConfig.FLASK_SEARCH_API
  private val apiHost = BuildConfig.STOCKX_API_HOST

  override suspend fun getSearch(search: String,
                                 navController: NavHostController,
                                 context: Context): Map<String, List<String>> {
    return try {
      client.get(searchURL) {
        url {
          parameters.append("search", search)
        }
      }
    } catch (e: RedirectResponseException) {
      // 3xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }
      mapOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }
      mapOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }
      mapOf()
    }
  }

  override suspend fun getTrends(query: String,
                                 currency: String,
                                 context: Context): List<Trend> {
    var apiKey: String
    withContext(Dispatchers.IO) {
      apiKey = client.get("$searchURL/api-key")
    }
    
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
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code:  " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
      }
      listOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
      }
      listOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
      }
      listOf()
    }
  }

  override suspend fun searchProduct(
    query: String,
    currency: String,
    country: String,
    quota: DailySearchQuota,
    searchModel: DailySearchViewModel,
    navController: NavHostController,
    context: Context
  ): Product {

    var apiKey: String
    withContext(Dispatchers.IO) {
      apiKey = client.get("$searchURL/api-key")
    }

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
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        searchModel.reverseSearch(quota)
      }
      blankProduct()

    } catch (e: ClientRequestException) {
      // 4xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code: " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        searchModel.reverseSearch(quota)
      }

      blankProduct()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      (context as Activity).runOnUiThread {
        Toast.makeText(
          context,
          "Server Error Code:  " +
                  "${e.response.status.value} " +
                  e.response.status.description,
          Toast.LENGTH_LONG
        ).show()
        searchModel.reverseSearch(quota)
      }
      blankProduct()
    }
  }
}