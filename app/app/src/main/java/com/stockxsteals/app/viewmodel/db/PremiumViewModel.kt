package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.PremiumDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PremiumViewModel
@Inject constructor (
  private val premiumDataSource: PremiumDataSource
): ViewModel() {

   suspend fun getIsPremium(): Int {
    return withContext(Dispatchers.IO) {
      premiumDataSource.getIsPremium()
    }
  }

   suspend fun setIsPremium(isPremium: Int) {
    withContext(Dispatchers.IO) {
      premiumDataSource.setIsPremium(isPremium)
    }
  }
}