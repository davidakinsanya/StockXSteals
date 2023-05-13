package com.stockxsteals.app.db.database

import com.stockxsteals.app.db.dao.FilterPresetsDAO


abstract class FilterPresetDatabase() {
  abstract fun filterPresetsDAO(): FilterPresetsDAO

  companion object {

  }

}