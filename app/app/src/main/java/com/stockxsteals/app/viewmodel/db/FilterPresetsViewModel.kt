package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.FilterPresetDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import db.entity.FilterPreset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FilterPresetsViewModel
@Inject constructor (
  private val filterPresetDataSource: FilterPresetDataSource
): ViewModel() {

  suspend fun getAllPresets(): Flow<List<FilterPreset>> {
    return withContext(Dispatchers.IO) {
      filterPresetDataSource.getAllPresets()
    }
  }

   suspend fun addPreset(
    country: String,
    currency: String,
    sizeType: String,
    size: Double
  ) {
    withContext(Dispatchers.IO) {
      filterPresetDataSource.addPreset(country, currency, sizeType, size)
    }
  }
}