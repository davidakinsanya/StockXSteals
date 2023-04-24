package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.viewmodel.FilterViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SwitchFilters(filterModel: FilterViewModel,
                  selected: String,
                  text: MutableState<String>,
                  focusManager: FocusManager,
                  focusRequester: FocusRequester,
                  keyboardController: SoftwareKeyboardController?) {

  when(selected) {
    "Country" -> {
      FilterByCountry(model = filterModel,
        selected = selected,
        text = text,
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController)
    }

    "Currency" -> {
      FilterByCurrency(model = filterModel,
        selected = selected,
        text = text,
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController)
    }

    "Size" -> {
      FilterBySize(model = filterModel,
        selected = selected,
        text = text,
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController)

    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterByCountry(model: FilterViewModel,
                    selected: String,
                    text: MutableState<String>,
                    focusManager: FocusManager,
                    focusRequester: FocusRequester,
                    keyboardController: SoftwareKeyboardController?
) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {
    FilterTextField(model = model,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterByCurrency(model: FilterViewModel,
                     selected: String,
                     text: MutableState<String>,
                     focusManager: FocusManager,
                     focusRequester: FocusRequester,
                     keyboardController: SoftwareKeyboardController?
) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {
    FilterTextField(model = model,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController)
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterBySize(model: FilterViewModel,
                 selected: String,
                 text: MutableState<String>,
                 focusManager: FocusManager,
                 focusRequester: FocusRequester,
                 keyboardController: SoftwareKeyboardController?
) {

  Row(modifier =
  Modifier
    .padding(top = 20.dp, start = 10.dp)) {

    FilterTextField(model = model,
      text = text,
      selected = selected,
      focusManager = focusManager,
      focusRequester = focusRequester,
      keyboardController = keyboardController)

    Spacer(Modifier.padding(start = 10.dp))

    SecondaryFilterTextField(model = model,
      text = text,
      selected = selected)
  }
}
