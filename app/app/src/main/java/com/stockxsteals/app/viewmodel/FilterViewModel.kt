package com.stockxsteals.app.viewmodel

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.model.filter.Size

class FilterViewModel: ViewModel() {

  private var searchWithFilters = SearchWithFilters("", "", "", "", "", 0.0)

  fun getCurrentSearch(): SearchWithFilters {
    return searchWithFilters
  }
  fun filterVariablesToString(): String {
    return "Search Params\n\n" +
            "Code: ${searchWithFilters.code} \n" +
            "Slug: ${searchWithFilters.slug} \n" +
            "Country: ${searchWithFilters.country} \n" +
            "Currency: ${searchWithFilters.currency} \n" +
            "Size: ${searchWithFilters.size.toString()}"
  }

  fun appendSlugOrCode(selected: String, text: String) {
    when (selected) {
      "Code" -> {
        searchWithFilters.code = text
      }
      "Slug" -> {
        searchWithFilters.slug = text
      }
    }
  }

  fun appendCountryAndCurrency(selected: String, text: String) {
    when (selected) {
      "Country" -> {
        searchWithFilters.country = text
      }
      "Currency" -> {
        searchWithFilters.currency = text
      }
    }
  }

  fun appendSize(size: Double, sizeType: String) {
    searchWithFilters.sizeType = sizeType
    searchWithFilters.size = size
  }

  fun getSizeLabels(): List<String> {
    val sizesList = mutableListOf<String>();
    Size.values().forEach {
      sizesList.add(it.type)
    }
    return sizesList
  }

  fun getSizesNumbers(type: String): List<Double> {
    return Size.valueOf(type).listOfSizes
  }

  fun getCurrencyType(): List<String> {
    val currencyList = mutableListOf<String>();
    Currency.values().forEach {
      currencyList.add(it.type)
    }
    return currencyList
  }

  fun getCurrencySymbol(type: String): String {
    return Currency.valueOf(type).symbol
  }
}