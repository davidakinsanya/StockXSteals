package com.stockxsteals.app.model.ui

import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.Trend

class ProductView(item: Any, navController: NavHostController) {

  private val product = if (navController.previousBackStackEntry?.destination?.route == "sneaker_search")
    item as Product
  else
    null

  private val trend = if (navController.previousBackStackEntry?.destination?.route == "trend")
    item as Trend
  else
    null

  fun getConstant(): List<String> {
    return if (product != null) {
      listOf(
        product.name,
        product.sku,
        product.image
      )
    } else {
      listOf(
        trend!!.name,
        trend.sku,
        trend.image
      )
    }
  }

  fun listForPager(): Map<String, List<Any>> {
    val map = HashMap<String, List<Any>>()
    return if (product != null) {
      map["1"] = listOf(
        product.description
      )

      map["2"] = listOf(
        product.variants,
      )

      map
    } else {
      map["1"] = listOf(
        trend!!.category
      )

      map["2"] = listOf(
        trend.release_date
      )

      map
    }
  }
}