package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.db.database.DailySearchDatabase
import com.stockxsteals.app.db.repository.DailySearchRepository
import kotlinx.coroutines.launch

class DailySearchViewModel(application: Application): AndroidViewModel(application) {
  private val readSearchNumber: LiveData<Int>
  private val repository: DailySearchRepository

  init {
    val dailySearchDAO = DailySearchDatabase.getDatabase(application).dailySearchDAO()

    repository = DailySearchRepository(dailySearchDAO)
    readSearchNumber = repository.readSearchNumber
  }

  fun appendSearch(newSearch: Int) {
    viewModelScope.launch {
      repository.appendSearch(newSearch)
    }
  }
}