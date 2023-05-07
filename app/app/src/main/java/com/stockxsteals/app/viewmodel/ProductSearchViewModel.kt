package com.stockxsteals.app.viewmodel

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductSearchViewModel: ViewModel() {
  private val _searchResult = MutableStateFlow(blankProduct())
  val searchResult: StateFlow<Product> = _searchResult


   suspend fun getProduct(slug: String,
                                 currency: String,
                                 country: String): Int {
    val res = RetrofitInstance
      .productSearchVariable
      .searchProduct(slug, currency, country)
      .execute()

    return if (res.isSuccessful) {
      _searchResult.emit(cleanUp(res.body()!!))
       1
    } else {
      println(res.code())
      -1
    }
  }

  private fun cleanUp(product: Product): Product {
    product.description = product.description.replace("\n<br>\n<br>\n", "\n")
    return product
  }
}