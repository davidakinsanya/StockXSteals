package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.db.database.FilterPresetDatabase
import com.stockxsteals.app.db.entity.FilterPreset
import com.stockxsteals.app.db.repository.FilterPresetsRepository
import kotlinx.coroutines.launch

class FilterPresetsViewModel(application: Application): AndroidViewModel(application) {
  // private val readAllPresets: LiveData<List<FilterPreset>>
  // private val repository: FilterPresetsRepository

  init {}

  fun addPreset(preset: FilterPreset) {
    viewModelScope.launch {}
  }
}