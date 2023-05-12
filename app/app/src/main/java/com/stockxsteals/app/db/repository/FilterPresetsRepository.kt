package com.stockxsteals.app.db.repository

import androidx.lifecycle.LiveData
import com.stockxsteals.app.db.dao.FilterPresetsDAO
import com.stockxsteals.app.db.entity.FilterPreset

class FilterPresetsRepository(private val filterPresetsDAO: FilterPresetsDAO) {
  val readAllPresets: LiveData<List<FilterPreset>> = filterPresetsDAO.readAllPresets()

  suspend fun addPreset(preset: FilterPreset) {
    filterPresetsDAO.addPreset(preset)
  }

}