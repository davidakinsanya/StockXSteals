package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.stockxsteals.app.http.doRequest
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import db.entity.DailySearchQuota

@Composable
fun SearchEntryCoroutineOnClick(productSearchViewModel: ProductSearchViewModel,
                                networkModel: NetworkViewModel,
                                context: Context,
                                noQuota: Boolean,
                                dailySearch: DailySearchViewModel,
                                displayItem: MutableState<Boolean>,
                                quota: DailySearchQuota?,
                                ) {
  LaunchedEffect(true) {
    val isPremium = productSearchViewModel.isPremium()
    if (networkModel.checkConnection(context)) {
      if (noQuota) {
        dailySearch.insertSearch()
        displayItem.value = true
      } else if (dailySearch.dbLogic(quota!!) == 1 || isPremium) {
        displayItem.value = true
        if (!isPremium) {
          Toast
            .makeText(
              context,
              "${quota.search_limit - quota.search_number} free daily searches left.",
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
      doRequest(
        result[0],
        productSearchViewModel.getFilterModel().getCurrentSearch().currency,
        productSearchViewModel.getFilterModel().getCurrentSearch().country
      )
    )
  }
}

@Composable
fun DeleteSearchCoroutine(deleteSearch: MutableState<Boolean>,
                          productSearchViewModel: ProductSearchViewModel) {
  LaunchedEffect(deleteSearch.value) {
    if (deleteSearch.value) {
      val search = productSearchViewModel.getHistoryModel().getSearchByStamp("0")
      productSearchViewModel.getHistoryModel().deleteSearch(search.id)
    }
  }
}