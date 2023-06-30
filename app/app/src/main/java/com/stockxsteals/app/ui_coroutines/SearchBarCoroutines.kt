package com.stockxsteals.app.ui_coroutines

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.stockxsteals.app.http.doRequest
import com.stockxsteals.app.model.filter.SearchWithFilters
import com.stockxsteals.app.navigation.AppScreens
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
    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun SearchComposableDB(produceSearch: MutableState<Boolean>,
                       productModel: ProductSearchViewModel,
                       navController: NavHostController,
                       text: String,
                       context: Context) {

  LaunchedEffect(key1 = produceSearch.value) {
    if (produceSearch.value) {
      val search = productModel.getFilterModel().getCurrentSearch()
      productModel.getHistoryModel().addSearch(
        timestamp = "0",
        country = search.country,
        currency = search.currency,
        sizeType = search.sizeType,
        size = search.size,
        name = "",
        image = "",
        json = "")
      navController.navigate(AppScreens.SneakerSearch.route)
    }
  }
  if (produceSearch.value)
    productModel.getFilterModel().setSearchResults(doRequest(text, navController, context))

}