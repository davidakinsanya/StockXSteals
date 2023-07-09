package com.stockxsteals.app.http

import android.app.Activity
import android.content.Context
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.stockxsteals.app.BuildConfig
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.model.dto.blankProduct
import com.stockxsteals.app.utils.errorEvent
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import db.entity.DailySearchQuota
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ApiServiceImpl(private val client: HttpClient): ApiService {

  private val baseUrl = BuildConfig.STOCKX_BASE_URL
  private val searchURL = BuildConfig.FLASK_SEARCH_API
  private val apiHost = BuildConfig.STOCKX_API_HOST
  private val apiKey = BuildConfig.STOCKX_API_KEY

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
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      (context as Activity).runOnUiThread {
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }

      mapOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      (context as Activity).runOnUiThread {
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }

      mapOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      (context as Activity).runOnUiThread {
        navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      }

      mapOf()
    }
  }

  override suspend fun getTrends(query: String,
                                 currency: String,
                                 context: Context): List<Trend> {
    
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
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      listOf()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      listOf()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

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
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      blankProduct()
    } catch (e: ClientRequestException) {
      // 4xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      blankProduct()
    } catch (e: ServerResponseException) {
      // 5xx - code response
      val firebase = FirebaseAnalytics.getInstance(context)
      errorEvent(firebase, e.response.status.value)

      blankProduct()
    }
  }
}