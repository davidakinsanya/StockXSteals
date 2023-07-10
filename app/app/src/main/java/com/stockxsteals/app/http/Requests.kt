package com.stockxsteals.app.http

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.blankProduct

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