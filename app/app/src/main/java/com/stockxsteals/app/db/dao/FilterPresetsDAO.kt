package com.stockxsteals.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stockxsteals.app.db.entity.FilterPreset

@Dao
internal interface FilterPresetsDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addPreset(preset: FilterPreset)

  @Query("SELECT * FROM filter_presets")
  fun readAllPresets(): LiveData<List<FilterPreset>>
}
