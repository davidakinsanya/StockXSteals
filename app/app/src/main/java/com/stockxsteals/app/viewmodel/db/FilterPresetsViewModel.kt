package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilterPresetsViewModel(application: Application): AndroidViewModel(application) {
  // private val readAllPresets: LiveData<List<FilterPreset>>
  // private val repository: FilterPresetsRepository

  init {}

  fun addPreset() {
    viewModelScope.launch {}
  }
}