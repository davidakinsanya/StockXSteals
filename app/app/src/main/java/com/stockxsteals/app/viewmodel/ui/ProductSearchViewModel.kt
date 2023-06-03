package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductSearchViewModel(private val filterModel: FilterViewModel,
                             private val searchModel: DailySearchViewModel,
                             private val historyModel: DailySearchHistoryViewModel,
                             private val premiumModel: PremiumViewModel,
                             private val uiModel: UIViewModel): ViewModel() {

  private val _searchResult = MutableStateFlow(blankProduct())
  val searchResult: StateFlow<Product> = _searchResult

  private val _trendSearch = MutableStateFlow(blankTrend())
  val trendSearch: StateFlow<Trend> = _trendSearch


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

  fun addProduct(product: Product) {
     viewModelScope.launch(Dispatchers.Default) {  // to run code in Background Thread
       _searchResult.emit(cleanUp(product))
     }
  }

  fun addTrend(trend: Trend) {
    viewModelScope.launch(Dispatchers.Default) {  // to run code in Background Thread
      _trendSearch.emit(trend)
    }
  }

  private fun cleanUp(product: Product): Product {
    product.description = product.description.replace("\n<br>\n<br>\n", "\n")
    return product
  }
}