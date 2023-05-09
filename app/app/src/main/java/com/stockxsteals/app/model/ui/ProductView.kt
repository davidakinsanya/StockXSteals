package com.stockxsteals.app.model.ui

import com.stockxsteals.app.model.dto.Product

class ProductView(private val product: Product) {

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
      product.traits,
    )

    return map
  }
}