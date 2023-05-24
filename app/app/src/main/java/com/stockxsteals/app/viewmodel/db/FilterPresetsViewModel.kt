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

  val allPreset = getAllPresets()


  private fun getAllPresets(): Flow<List<FilterPreset>> {
    return filterPresetDataSource.getAllPresets()

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

  suspend fun deletePreset(id: Long) {
    withContext(Dispatchers.IO) {
      filterPresetDataSource.deletePreset(id)
    }
  }

  fun presetExists(list: List<FilterPreset>, country: String,
                   currency: String,
                   sizeType: String,
                   size: Double): Boolean {
    
    list.forEach {
      if (it.country == country
        && it.currency == currency
        && it.sizeType == sizeType
        && it.size == size
      ) return true
    }
    return false
  }
}