package com.stockxsteals.app.viewmodel

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.model.filter.ShoeSize

class FilterViewModel: ViewModel() {

  private var searchWithFilters = SearchWithFilters("", "", "", "", "", 0.0)

  fun getCurrentSearch(): SearchWithFilters {
    return searchWithFilters
  }

  fun searchCheck(): Boolean {
    return searchWithFilters.country.isNotEmpty() &&
          searchWithFilters.currency.isNotEmpty() &&
          searchWithFilters.size != 0.0 &&
            (searchWithFilters.code.isNotEmpty() || searchWithFilters.slug.isNotEmpty())
  }

  fun filterVariablesToString(): String {
    return "Search Params\n" +
            "Code: ${searchWithFilters.code} \n" +
            "Slug: ${searchWithFilters.slug} \n" +
            "Country: ${searchWithFilters.country} \n" +
            "Currency: ${searchWithFilters.currency} \n" +
            "Size: ${searchWithFilters.size}"
  }

  fun getFilterMap(): HashMap<String,List<Any>> {
    val listMap: HashMap<String, List<Any>> = HashMap()

    listMap["Country"] = arrayListOf()
    listMap["Currency"] = arrayListOf()
    listMap["Size"] = arrayListOf()

    listMap.keys.forEach { entry ->

      when(entry) {
        "Country" -> {
          listMap["Country"] = this.getCountry()
        }
        "Currency" -> {
          listMap["Currency"] = this.getCurrencyType().toList()
        }
        "Size" -> {
          listMap["Size"] = this.getSizeLabels().toList()
        }
      }
    }
    return listMap
  }

  private fun getSizeLabels(): Array<ShoeSize> {
    return ShoeSize.values()
  }

  private fun getCurrencyType(): Array<Currency> {
    return Currency.values()
  }

  private fun getCountry(): List<String> {
    return java.util.Locale.getISOCountries().asList()
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

  fun appendCountryAndCurrency(selected: String?, text: String?) {
    when (selected) {
      "Country" -> {
        if (text != null) searchWithFilters.country = text
      }
      "Currency" -> {
        if (text != null) searchWithFilters.currency = text
      }
    }
  }

  fun appendSize(size: Double?, sizeType: String?) {
    if (sizeType != null) searchWithFilters.sizeType = sizeType
    if (size != null) searchWithFilters.size = size

  }
}