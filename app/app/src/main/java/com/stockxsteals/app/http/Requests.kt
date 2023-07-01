package com.stockxsteals.app.http

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.blankProduct
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import db.entity.DailySearchQuota

private val service = ApiService.create()

@Composable
fun doRequest(search: String,
              navController: NavHostController,
              context: Context
): Map<String, List<String>> {
  val data = produceState<Map<String, List<String>>>(
    initialValue = emptyMap(),
    producer = { value = service.getSearch(
      search,
      navController,
      context)
    }
  )
  return data.value
}


@Composable
fun doRequest(slug: String,
              currency: String,
              country: String,
              searchModel: DailySearchViewModel,
              navController: NavHostController,
              context: Context,
              quota: DailySearchQuota): Product {

  val data = produceState(
    initialValue = blankProduct(),
    producer = { value = service.searchProduct(
      slug,
      currency,
      country,
      quota,
      searchModel,
      navController,
      context) }
  )
  return data.value
}