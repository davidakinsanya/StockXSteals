package com.stockxsteals.server.dao.api

import com.stockxsteals.server.dto.api.Product
import com.stockxsteals.server.http.RetrofitInstance

class ProductSearchDAO {
  
  fun executeSearch(slug: String,
                    currency: String,
                    country: String): Product? {
    
    val res = RetrofitInstance.productSearchVariable.searchProduct(slug, currency, country).execute()
    
    return if (res.isSuccessful) cleanUp(res.body()!!)
    else {
      println(res.errorBody())
      return null
    }
  }
  
  private fun cleanUp(product: Product): Product {
    product.description = product.description.replace("\n<br>\n<br>\n", "\n")
    return product
  }
}