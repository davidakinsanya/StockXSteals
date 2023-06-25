package com.stockxsteals.app.model.ui

import com.stockxsteals.app.model.dto.Product

class ProductView(item: Any) {

  private val product = item as Product

  fun getConstant(): List<String> {
    return listOf(
        product.name,
        product.sku,
        product.image
      )
  }

  fun listForPager(): Map<String, List<Any>> {
    val map = HashMap<String, List<Any>>()
    map["1"] = listOf(
      product.description
    )

    map["2"] = listOf(
      product.traits
    )

    map["3"] = listOf(
      product.variants,
    )

    map["4"] = listOf(
      product.market
    )

    return map
  }
}