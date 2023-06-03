package com.stockxsteals.app.viewmodel.ui


import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.model.filter.SearchWithFilters
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.model.filter.ShoeSize
import com.stockxsteals.app.viewmodel.db.FilterPresetsViewModel
import db.entity.FilterPreset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilterViewModel(private val presetModel: FilterPresetsViewModel)
  :ViewModel() {

  private var searchWithFilters = SearchWithFilters("", "", "", "", 0.0)
  private val _bootMap =  MutableStateFlow(mapOf<String, List<String>>())
  var bootMap: StateFlow<Map<String, List<String>>> = _bootMap

  fun filterVariablesToString(): String {
    return "Search Params\n" +
            "Slug: ${searchWithFilters.slug} \n" +
            "Country: ${searchWithFilters.country} \n" +
            "Currency: ${searchWithFilters.currency} \n" +
            "Size: ${searchWithFilters.size}"
  }

  fun getPresetsModel(): FilterPresetsViewModel {
    return presetModel
  }

  fun getCurrentSearch(): SearchWithFilters {
    return searchWithFilters
  }

  fun searchCheck(): Boolean {
    return searchWithFilters.country.isNotEmpty() &&
            searchWithFilters.currency.isNotEmpty() &&
            searchWithFilters.size != 0.0
  }

  fun getFilterMap(): HashMap<String, List<Any>> {
    val listMap: HashMap<String, List<Any>> = HashMap()

    listMap["Country"] = arrayListOf()
    listMap["Currency"] = arrayListOf()
    listMap["Size"] = arrayListOf()

    listMap.keys.forEach { entry ->

      when (entry) {
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

  fun appendCountryAndCurrency(selected: String?, text: String?, count: MutableState<Int>?): Int {
    when (selected) {
      "Country" -> {
        if (text != null) {
          searchWithFilters.country = text
          if (count != null) count.value++
        }
      }
      "Currency" -> {
        if (text != null) {
          searchWithFilters.currency = text
          if (count != null) count.value++
        }
      }
    }
    return count?.value ?: -1
  }

  fun appendSize(size: Double?, sizeType: String?, count: MutableState<Int>?): Int {
    if (sizeType != null) searchWithFilters.sizeType = sizeType
    if (size != null) {
      searchWithFilters.size = size
      if (count != null) count.value++
    }
    if (size != null) if (count != null) return count.value
    return -1
  }

  fun setSearchResults(result: Map<String, List<String>>) {
    viewModelScope.launch(Dispatchers.Default) {  // to run code in Background Thread
        _bootMap.emit(result)
    }
  }

  fun addPreset(preset: FilterPreset, count: MutableState<Int>) {
    count.value = this.appendCountryAndCurrency("Country", preset.country, count)
    count.value = this.appendCountryAndCurrency("Currency", preset.currency, count)
    this.appendSize(null, preset.sizeType, null) // append Size Type
    count.value = this.appendSize(preset.size, null, count)
  }
}
