package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.db.database.DailySearchDatabase
import com.stockxsteals.app.db.database.PremiumDatabase
import com.stockxsteals.app.db.repository.DailySearchRepository
import com.stockxsteals.app.db.repository.PremiumRepository
import kotlinx.coroutines.launch

class PremiumViewModel(application: Application): AndroidViewModel(application) {
  private val readIsPremium: LiveData<Boolean>
  private val repository: PremiumRepository

  init {
    val premiumDAO = PremiumDatabase.getDatabase(application).premiumDAO()

    repository = PremiumRepository(premiumDAO)
    readIsPremium = repository.readIsPremium
  }

  fun setPremium(isPremium: Boolean) {
    viewModelScope.launch {
      repository.setPremium(isPremium)
    }
  }
}