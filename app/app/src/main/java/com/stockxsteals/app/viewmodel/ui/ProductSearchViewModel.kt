package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class ProductSearchViewModel(private val searchModel: DailySearchViewModel): ViewModel(), java.io.Serializable {
  private val _searchResult = MutableStateFlow(blankProduct())
  val searchResult: StateFlow<Product> = _searchResult

  fun getSearchModel(): DailySearchViewModel {
    return searchModel
  }

   suspend fun getProduct(slug: String,
                                 currency: String,
                                 country: String): Int = withContext(Dispatchers.IO) {
     val res = RetrofitInstance
      .productSearchVariable
      .searchProduct(slug, currency, country)
      .execute()

     return@withContext if (res.isSuccessful) {
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