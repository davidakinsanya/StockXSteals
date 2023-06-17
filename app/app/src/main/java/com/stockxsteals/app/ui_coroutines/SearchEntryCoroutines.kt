package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import db.entity.DailySearchQuota
import db.entity.Premium

@Composable
fun SearchEntryCoroutineOnClick(networkModel: NetworkViewModel,
                                context: Context,
                                dailySearch: DailySearchViewModel,
                                displayItem: MutableState<Boolean>,
                                searchQuota: DailySearchQuota,
                                premiumQuota: Premium
                                ) {


  LaunchedEffect(true) {
    if (networkModel.checkConnection(context)) {
      if (dailySearch.dbLogic(searchQuota) == 1 || premiumQuota.isPremium.toInt() == 1) {
        displayItem.value = true
        if (premiumQuota.isPremium.toInt() == 0) {
          Toast
            .makeText(
              context,
              "${searchQuota.search_limit - searchQuota.search_number} free daily searches left.",
              Toast.LENGTH_LONG
            )
            .show()
        }
      } else {
        Toast
          .makeText(context, "Please upgrade to L8test+.", Toast.LENGTH_LONG)
          .show()
      }
    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun SearchEntryCoroutineDB(displayItem: MutableState<Boolean>,
                           productSearchViewModel: ProductSearchViewModel,
                           result: List<String>,
                           navController: NavHostController,
                           searchRoute: String,
                           ) {

  LaunchedEffect(key1 = displayItem.value) {
    if (displayItem.value) {
      val currentSearch = productSearchViewModel.getHistoryModel().getSearchByStamp("0")
      if (currentSearch != null)
        productSearchViewModel
          .getHistoryModel()
          .updateSearch(
            timestamp = getCurrentDate(),
            country = currentSearch.country,
            currency = currentSearch.currency,
            sizeType = currentSearch.sizeType,
            size = currentSearch.size,
            name = result[0],
            image = result[1],
            json = "",
            id = currentSearch.id)
      navController.navigate(searchRoute)
    }
  }

  if (displayItem.value) {
    productSearchViewModel.addProduct(
      result[0],
      productSearchViewModel.getFilterModel().getCurrentSearch().currency,
      productSearchViewModel.getFilterModel().getCurrentSearch().country
    )
  }
}

@Composable
fun DeleteSearchCoroutine(deleteSearch: MutableState<Boolean>,
                          productSearchViewModel: ProductSearchViewModel) {
  LaunchedEffect(deleteSearch.value) {
    if (deleteSearch.value) {
      productSearchViewModel.getHistoryModel().deleteSearch("0")
      productSearchViewModel.getFilterModel().clearFilterVariables()
    }
  }
}