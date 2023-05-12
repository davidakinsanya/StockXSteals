package com.stockxsteals.app.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stockxsteals.app.db.dao.FilterPresetsDAO
import com.stockxsteals.app.db.entity.FilterPreset


@Database(entities = [FilterPreset::class], version = 1, exportSchema = false)
abstract class FilterPresetDatabase: RoomDatabase() {
  abstract fun filterPresetsDAO(): FilterPresetsDAO

  companion object {
    private var INSTANCE: FilterPresetDatabase? = null

    fun getDatabase(context: Context): FilterPresetDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          FilterPresetDatabase::class.java,
          "filter_presets"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }

}