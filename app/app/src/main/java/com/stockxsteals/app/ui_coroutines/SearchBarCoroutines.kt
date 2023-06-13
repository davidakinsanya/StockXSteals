package com.stockxsteals.app.ui_coroutines

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.stockxsteals.app.http.doRequest
import com.stockxsteals.app.model.filter.SearchWithFilters
import com.stockxsteals.app.viewmodel.db.FilterPresetsViewModel
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import db.entity.FilterPreset

@Composable
fun SearchCoroutineOnClick(networkModel: NetworkViewModel,
                           context: Context,
                           presetModel: FilterPresetsViewModel,
                           allPresets: List<FilterPreset>,
                           currentSearch: SearchWithFilters,
                           produceSearch: MutableState<Boolean>
                    ) {
  LaunchedEffect(key1 = true) {
    if (networkModel.checkConnection(context)) {
      val presetExists = presetModel.presetExists(
        allPresets,
        currentSearch.country,
        currentSearch.currency,
        currentSearch.sizeType,
        currentSearch.size
      )

      if (!presetExists) {
        presetModel.addPreset(
          currentSearch.country,
          currentSearch.currency,
          currentSearch.sizeType,
          currentSearch.size
        )
      }
      produceSearch.value = true
      println("onClick() " + produceSearch.value)
    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun SearchComposableDB(produceSearch: MutableState<Boolean>,
                       productSearchViewModel: ProductSearchViewModel,
                       navController: NavHostController,
                       sneakersDestination: String,
                       text: String) {

  LaunchedEffect(key1 = produceSearch.value) {
    if (produceSearch.value) {
      val search = productSearchViewModel.getFilterModel().getCurrentSearch()
      productSearchViewModel.getHistoryModel().addSearch(
        timestamp = "0",
        country = search.country,
        currency = search.currency,
        sizeType = search.sizeType,
        size = search.size,
        name = "",
        image = "",
        json = "")
      navController.navigate(sneakersDestination)
    }
  }
  if (produceSearch.value)
    productSearchViewModel.getFilterModel().setSearchResults(doRequest(text))

}