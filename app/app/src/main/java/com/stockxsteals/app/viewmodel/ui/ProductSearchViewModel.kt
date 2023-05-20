package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class ProductSearchViewModel(private val filterModel: FilterViewModel,
                             private val searchModel: DailySearchViewModel,
                             private val historyModel: DailySearchHistoryViewModel,
                             private val premiumModel: PremiumViewModel,
                             private val uiModel: UIViewModel): ViewModel(), java.io.Serializable {

  private val _searchResult = MutableStateFlow(blankProduct())
  val searchResult: StateFlow<Product> = _searchResult


  fun getFilterModel(): FilterViewModel {
    return filterModel
  }

  fun getSearchModel(): DailySearchViewModel {
    return searchModel
  }

  fun getHistoryModel(): DailySearchHistoryViewModel {
    return historyModel
  }

  fun getUIModel(): UIViewModel {
    return uiModel
  }

  private fun getPremiumModel(): PremiumViewModel {
    return premiumModel
  }

  suspend fun isPremium(): Boolean {
    return withContext(Dispatchers.IO) {
       getPremiumModel().getIsPremium() == 1
    }
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