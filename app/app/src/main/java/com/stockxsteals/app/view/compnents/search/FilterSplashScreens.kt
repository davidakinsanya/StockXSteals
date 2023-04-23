package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.viewmodel.FilterViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SwitchFilters(selected: String,
                  filterObj: SearchWithFilters,
                  navController: NavHostController,
                  text: MutableState<String>,
                  focusManager: FocusManager,
                  focusRequester: FocusRequester,
                  keyboardController: SoftwareKeyboardController?,
                  searchRoute: String) {

  when(selected) {
    "Country" -> {
      FilterByCountry(
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
      FilterByCurrency(selected = selected,
        filterObj = filterObj,
        navController = navController,
        text = remember { mutableStateOf(filterObj.country) },
        focusManager = focusManager,
        focusRequester = focusRequester,
        keyboardController = keyboardController,
        searchRoute = searchRoute)
    }

    "Size" -> {
      FilterBySize(selected = selected,
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
fun FilterByCountry(selected: String,
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
    FilterTextField(model = FilterViewModel(),
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
fun FilterByCurrency(
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
    FilterTextField(model = FilterViewModel(),
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
fun FilterBySize(selected: String,
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

    FilterTextField(model = FilterViewModel(),
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun FilterTextField(model: FilterViewModel,
                    selected: String,
                    filterObj: SearchWithFilters,
                    navController: NavHostController,
                    text: MutableState<String>,
                    focusManager: FocusManager,
                    focusRequester: FocusRequester,
                    keyboardController: SoftwareKeyboardController?,
                    searchRoute: String) {

  var selectedItem = remember { mutableStateOf("") }
  val expanded = remember { mutableStateOf(false) }
  val textFieldSize = remember { mutableStateOf(Size.Zero) }
  val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp
  else Icons.Filled.KeyboardArrowDown
  val filterMap = model.getFilterMap()




  val mauve = Color(224, 176, 255)

  BasicTextField(
    value = text.value,
    maxLines = 1,
    onValueChange = { text.value = it; },
    enabled = true,
    modifier = Modifier
      .onGloballyPositioned { coordinates ->
        textFieldSize.value = coordinates.size.toSize()
      }
      .height(35.dp)
      .fillMaxWidth(.5f)
      .border(
        border = BorderStroke(
          width = 1.5.dp,
          color = mauve
        ),
        shape = RoundedCornerShape(50.dp)
      ),
    keyboardActions = KeyboardActions(),
    keyboardOptions = KeyboardOptions()) {

      val interactionSource = remember { MutableInteractionSource() }
      TextFieldDefaults.TextFieldDecorationBox(
        value = text.value,
        innerTextField = it,
        singleLine = true,
        enabled = true,
        visualTransformation = VisualTransformation.None,
        placeholder = {
          Text(
            text = selected,
            fontSize = 16.sp,
          )
        },

        trailingIcon = {
          Icon(icon, "", Modifier.clickable { expanded.value = !expanded.value })
        },
        interactionSource = interactionSource,
        // keep horizontal paddings but change the vertical
        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
          top = 0.dp, bottom = 0.dp
        ),
      )

      DropdownMenu(expanded = expanded.value,
        onDismissRequest = {expanded.value = false },
        modifier = Modifier
          .width(with(LocalDensity.current){textFieldSize.value.width.toDp()})
          .height(130.dp)) {

        filterMap[selected]?.forEach { label ->
          DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text(text = label.toString())
          }
        }
      }
    }
  }
