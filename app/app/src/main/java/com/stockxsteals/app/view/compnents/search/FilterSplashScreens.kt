package com.stockxsteals.app.view.compnents.search

import android.util.Log
import androidx.compose.runtime.Composable
import com.stockxsteals.app.model.SearchWithFilters

@Composable
fun SwitchFilters(selected: String, filterObj: SearchWithFilters) {
  when(selected) {
    "Country" -> {
      FilterByCountry(filterObj = filterObj)
    }

    "Currency" -> {
      FilterByCurrency(filterObj = filterObj)
    }

    "Size" -> {
      FilterBySize(filterObj = filterObj)

    }
  }
}

@Composable
fun FilterByCountry(filterObj: SearchWithFilters) { Log.d("country", "1") }

@Composable
fun FilterByCurrency(filterObj: SearchWithFilters) { Log.d("currency", "2") }

@Composable
fun FilterBySize(filterObj: SearchWithFilters) { Log.d("size", "3") }