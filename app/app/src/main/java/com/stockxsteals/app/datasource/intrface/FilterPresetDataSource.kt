package com.stockxsteals.app.datasource.intrface


import db.entity.FilterPreset
import kotlinx.coroutines.flow.Flow


interface FilterPresetDataSource {

  suspend fun getAllPresets(): Flow<List<FilterPreset>>

  suspend fun addPreset(country: String, currency: String, sizeType: String, size: Double)

  suspend fun deletePreset(id: Long)
}
