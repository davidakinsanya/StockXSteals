package com.stockxsteals.server.dao.api

import com.stockxsteals.server.dto.api.ProductActivityData
import com.stockxsteals.server.http.RetrofitInstance

class ProductActivitySearchDAO {
  fun executeSearch(slug: String,
                    type: String,
                    currency: String,
                    country: String): ProductActivityData? {
    
    val res = RetrofitInstance
      .productActivitySearchVariable
      .searchProductActivity(slug, type, currency, country)
      .execute()
    
    return if (res.isSuccessful) res.body()!!
    else {
      println(res.errorBody())
      return null
    }
  }
}