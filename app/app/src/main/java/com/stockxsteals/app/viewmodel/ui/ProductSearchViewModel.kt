package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stockxsteals.app.http.ApiService
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import db.entity.DailySearchQuota
import db.entity.Premium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductSearchViewModel(private val filterModel: FilterViewModel,
                             private val searchModel: DailySearchViewModel,
                             private val historyModel: DailySearchHistoryViewModel,
                             private val premiumModel: PremiumViewModel,
                             private val qonversionModel: QonversionViewModel,
                             private val uiModel: UIViewModel): ViewModel() {

  private val _searchResult = MutableStateFlow(blankProduct())
  val searchResult: StateFlow<Product> = _searchResult

  private var searchQuota: DailySearchQuota? = null
  private var currentTrend: Trend = blankTrend()

  fun getFilterModel(): FilterViewModel { return filterModel }

  fun getSearchModel(): DailySearchViewModel { return searchModel }

  fun getHistoryModel(): DailySearchHistoryViewModel { return historyModel }

  fun getPremiumModel(): PremiumViewModel { return premiumModel }

  fun getUIModel(): UIViewModel { return uiModel }

  private fun getQonversionModel(): QonversionViewModel { return qonversionModel }

  fun setDailySearchQuota(quota: DailySearchQuota) { searchQuota = quota }

  fun getDailySearchQuota(): DailySearchQuota { return searchQuota!! }

  fun clearQuota() { searchQuota = null }

  fun setCurrentTrends(trend: Trend) { currentTrend = trend }

  fun getCurrentTrends(): Trend { return currentTrend }

  fun clearTrends() { currentTrend = blankTrend() }

  suspend fun isPremium(premium: List<Premium>): Boolean {
    withContext(Dispatchers.IO) {
       if (premium.isEmpty()) {
         getPremiumModel().newPremiumQuota()
         return@withContext false
       }
       return@withContext getPremiumModel().getIsPremium(premium[0].id) == 1 &&
                          getQonversionModel().hasPremiumPermission
    }
    return false
  }

  suspend fun insertFirstSearch(quota: List<DailySearchQuota>) {
    withContext(Dispatchers.IO) {
      if(quota.isEmpty()) {
        getSearchModel().insertSearch()
      }
    }
  }

  fun addProduct(slug: String,
                 country: String,
                 currency: String,
                 quota: DailySearchQuota,
                 navController: NavHostController,
                 context: Context
  ) {
     val service = ApiService.create()

     viewModelScope.launch(Dispatchers.IO) { // to run code in Background Thread
       val result = service.searchProduct(
         slug,
         country,
         currency,
         quota,
         getSearchModel(),
         navController,
         context,
       )
       _searchResult.emit(cleanUp(result))
     }
  }

  fun clearProduct() {
    viewModelScope.launch(Dispatchers.Default) { // to run code in Background Thread
      _searchResult.emit(blankProduct())
    }
  }

  private fun cleanUp(product: Product): Product {
    product.description = product.description.replace("\n<br>\n<br>\n", "\n")
    product.description = product.description.replace("The", "\nThe")

    return product
  }
}