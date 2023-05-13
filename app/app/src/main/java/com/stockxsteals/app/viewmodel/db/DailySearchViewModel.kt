package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.db.database.DailySearchDatabase
import com.stockxsteals.app.db.repository.DailySearchRepository
import kotlinx.coroutines.launch

class DailySearchViewModel(application: Application): AndroidViewModel(application) {
  // private val readSearchNumber: LiveData<Int>
  // private val readSearchLimit: LiveData<Int>
  // private val readTimeStamp: LiveData<String>
  // private val repository: DailySearchRepository

  init {}

  fun appendSearch(newSearch: Int) {
    viewModelScope.launch {}
  }

  fun addSearchLimit(limit: Int) {
    viewModelScope.launch {}
  }

  fun addTimeStamp(timestamp: String) {
    viewModelScope.launch {}
  }
}