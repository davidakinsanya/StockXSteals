package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.db.database.DailySearchDatabase
import com.stockxsteals.app.db.repository.DailySearchRepository
import kotlinx.coroutines.launch
import java.sql.Timestamp

class DailySearchViewModel(application: Application): AndroidViewModel(application) {
  private val readSearchNumber: LiveData<Int>
  private val readSearchLimit: LiveData<Int>
  private val readTimeStamp: LiveData<String>
  private val repository: DailySearchRepository

  init {
    val dailySearchDAO = DailySearchDatabase.getDatabase(application).dailySearchDAO()
    repository = DailySearchRepository(dailySearchDAO)

    readSearchNumber = repository.readSearchNumber
    readSearchLimit = repository.readSearchLimit
    readTimeStamp = repository.readTimeStamp
  }

  fun appendSearch(newSearch: Int) {
    viewModelScope.launch {
      repository.appendSearch(newSearch)
    }
  }

  fun addSearchLimit(limit: Int) {
    viewModelScope.launch {
      repository.addSearchLimit(limit)
    }
  }

  fun addTimeStamp(timestamp: String) {
    viewModelScope.launch {
      repository.addTimeStamp(timestamp)
    }
  }
}