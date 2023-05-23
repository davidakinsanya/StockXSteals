package com.stockxsteals.app.datasource.impl

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.FilterPresetDataSource
import db.entity.FilterPreset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FilterPresetImpl(db: Database): FilterPresetDataSource {
  private val queries = db.filterPresetQueries

  override fun getAllPresets(): Flow<List<FilterPreset>> {
    return queries.getAllPresets().asFlow().mapToList()
  }

  override suspend fun addPreset(
    country: String,
    currency: String,
    sizeType: String,
    size: Double
  ) {
    withContext(Dispatchers.IO) {
      queries.addPreset(country, currency, sizeType, size)
    }
  }

  override suspend fun deletePreset(id: Long) {
    withContext(Dispatchers.IO) {
      queries.deletePreset(id)
    }
  }
}