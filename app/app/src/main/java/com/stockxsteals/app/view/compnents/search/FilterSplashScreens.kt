package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.viewmodel.FilterViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SwitchFilters(filterModel: FilterViewModel,
                  selected: String,
                  filterObj: SearchWithFilters,
                  navController: NavHostController,
                  text: MutableState<String>,
                  focusManager: FocusManager,
                  focusRequester: FocusRequester,
                  keyboardController: SoftwareKeyboardController?,
                  searchRoute: String) {

  when(selected) {
    "Country" -> {
      FilterByCountry(model = filterModel,
        selected = selected,
        filterObj = filterObj,
        navController = navController,
        text = remember { mutableStateOf(filterObj.country) },
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController,
        searchRoute = searchRoute)
    }

    "Currency" -> {
      FilterByCurrency(model = filterModel,
        selected = selected,
        filterObj = filterObj,
        navController = navController,
        text = remember { mutableStateOf(filterObj.country) },
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController,
        searchRoute = searchRoute)
    }

    "Size" -> {
      FilterBySize(model = filterModel,
        selected = selected,
        filterObj = filterObj,
        navController = navController,
        text = remember { mutableStateOf(filterObj.country) },
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController,
        searchRoute = searchRoute)

    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterByCountry(model: FilterViewModel,
                    selected: String,
                    filterObj: SearchWithFilters,
                    navController: NavHostController,
                    text: MutableState<String>,
                    focusManager: FocusManager,
                    focusRequester: FocusRequester,
                    keyboardController: SoftwareKeyboardController?,
                    searchRoute: String) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {
    FilterTextField(model = model,
      filterObj = filterObj,
      navController = navController,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController,
      searchRoute = searchRoute
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterByCurrency(model: FilterViewModel,
                     selected: String,
                     filterObj: SearchWithFilters,
                     navController: NavHostController,
                     text: MutableState<String>,
                     focusManager: FocusManager,
                     focusRequester: FocusRequester,
                     keyboardController: SoftwareKeyboardController?,
                     searchRoute: String) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {
    FilterTextField(model = model,
      navController = navController,
      filterObj = filterObj,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController,
      searchRoute = searchRoute
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterBySize(model: FilterViewModel,
                 selected: String,
                 filterObj: SearchWithFilters,
                 navController: NavHostController,
                 text: MutableState<String>,
                 focusManager: FocusManager,
                 focusRequester: FocusRequester,
                 keyboardController: SoftwareKeyboardController?,
                 searchRoute: String) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {

    FilterTextField(model = model,
      filterObj = filterObj,
      navController = navController,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController,
      searchRoute = searchRoute
    )

    Spacer(Modifier.padding(start = 10.dp))

    SecondaryFilterTextField(model = model,
      navController = navController,
      filterObj = filterObj,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController,
      searchRoute = searchRoute
    )
  }
}
