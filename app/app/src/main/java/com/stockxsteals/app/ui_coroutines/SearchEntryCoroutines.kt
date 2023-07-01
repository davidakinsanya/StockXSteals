package com.stockxsteals.app.ui_coroutines

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import db.entity.DailySearchQuota
import db.entity.Premium

@Composable
fun SearchEntryCoroutineOnClick(networkModel: NetworkViewModel,
                                productModel: ProductSearchViewModel,
                                displayItem: MutableState<Boolean>,
                                searchQuota: DailySearchQuota,
                                premiumQuota: Premium,
                                navController: NavHostController,
                                context: Context,
                                result: List<String>,
                                resultBool: Boolean
                                ) {


  LaunchedEffect(true) {
    if (networkModel.checkConnection(context)) {
      if (productModel.getSearchModel().dbLogic(searchQuota, premiumQuota.isPremium.toInt()) == 1) {
        if (premiumQuota.isPremium.toInt() == 0 && resultBool) {
          val diff = searchQuota.search_limit - searchQuota.search_number
          val toast = if (diff.toInt() == 0)
            "Please upgrade to L8test+."
          else
            "$diff free daily searches left."
          Toast
            .makeText(
              context,
              toast,
              Toast.LENGTH_LONG
            )
            .show()
        }
        displayItem.value = true
      } else {
        navController
          .currentBackStackEntry
          ?.savedStateHandle
          ?.set("search_result", result)

        productModel.setDailySearchQuota(searchQuota)
        navController.navigate(AppScreens.Premium.route)
      }
    } else {
      networkModel.toastMessage(context)
    }
  }
}

@Composable
fun SearchEntryCoroutineDB(displayItem: MutableState<Boolean>,
                           productModel: ProductSearchViewModel,
                           result: List<String>,
                           searchQuota: DailySearchQuota,
                           navController: NavHostController,
                           context: Context
                           ) {
  LaunchedEffect(key1 = displayItem.value) {
    if (displayItem.value) {
      val currentSearch = productModel.getHistoryModel().getSearchByStamp("0")
      if (currentSearch != null)
        productModel
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
      navController.navigate(AppScreens.Search.route)
    }
  }

  if (displayItem.value) {
    productModel.addProduct(
      result[0],
      productModel.getFilterModel().getCurrentSearch().currency,
      productModel.getFilterModel().getCurrentSearch().country,
      searchQuota,
      navController,
      context
    )
  }
}

@Composable
fun DeleteSearchCoroutine(deleteSearch: MutableState<Boolean>,
                          productModel: ProductSearchViewModel) {
  LaunchedEffect(deleteSearch.value) {
    if (deleteSearch.value) {
      productModel.getHistoryModel().deleteSearch("0")
      productModel.getFilterModel().clearFilterVariables()
    }
  }
}