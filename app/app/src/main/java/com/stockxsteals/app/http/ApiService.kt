package com.stockxsteals.app.http

import android.content.Context
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import db.entity.DailySearchQuota
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface ApiService {

  suspend fun getSearch(search: String): Map<String, List<String>>

  suspend fun getTrends(query: String,
                        currency: String,
                        navController: NavHostController,
                        context: Context): List<Trend>

  suspend fun searchProduct(query: String,
                            currency: String,
                            country: String,
                            quota: DailySearchQuota,
                            searchModel: DailySearchViewModel,
                            navController: NavHostController,
                            context: Context
  ): Product

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