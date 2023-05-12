package com.stockxsteals.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DailySearchDAO {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun appendSearch(newSearch: Int)

  @Query("SELECT search_number FROM daily_search")
  fun getSearchNumber(): LiveData<Int>
}