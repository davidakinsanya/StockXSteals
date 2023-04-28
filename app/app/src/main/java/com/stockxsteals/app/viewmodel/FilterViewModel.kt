package com.stockxsteals.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.model.filter.ShoeSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilterViewModel: ViewModel(), java.io.Serializable {

  private var searchWithFilters = SearchWithFilters("", "", "", "", 0.0)
  private val _bootMap = MutableStateFlow(mutableMapOf<PyObject, PyObject>())
  var bootMap: StateFlow<MutableMap<PyObject, PyObject>> = _bootMap


  fun getCurrentSearch(): SearchWithFilters {
    return searchWithFilters
  }

  fun searchCheck(): Boolean {
    return searchWithFilters.country.isNotEmpty() &&
            searchWithFilters.currency.isNotEmpty() &&
            searchWithFilters.size != 0.0
  }

  fun filterVariablesToString(): String {
    return "Search Params\n" +
            "Slug: ${searchWithFilters.slug} \n" +
            "Country: ${searchWithFilters.country} \n" +
            "Currency: ${searchWithFilters.currency} \n" +
            "Size: ${searchWithFilters.size}"
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

  fun setSearchResults(search: String) {
    viewModelScope.launch(Dispatchers.Default) {  // to run code in Background Thread
      val python = Python.getInstance()
      val pythonFile = python.getModule("search")
      val map = pythonFile.callAttr("stockx_search", search).asMap()
      _bootMap.emit(map)
    }
  }
}
