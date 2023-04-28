package com.stockxsteals.app.view.compnents.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stockxsteals.app.R
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.viewmodel.FilterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(navController: NavHostController, filterModel: FilterViewModel) {

  val screens = listOf(
    AppScreens.Trends,
    AppScreens.Search,
    AppScreens.Settings,
  )

  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val searchRoute = AppScreens.TopSearch.route
  var searchScreen = false
  var selected = ""

  screens.forEach { _ ->
    searchScreen = currentDestination?.hierarchy?.any {
      it.route == searchRoute
    } == true
  }

  screens.forEach { screen ->
    val tempSelected = currentDestination?.hierarchy?.any {
      it.route == screen.route
    } == true
    if (tempSelected) {
      selected = screen.title
    }
  }

  TopAppBar(backgroundColor = Color(0xFFFFFFFF)) {
    val text = remember { mutableStateOf(selected) } // TODO: In future retrieve from ViewModel

    Row(modifier = Modifier.padding(start = 10.dp)) {

      Image(painter = painterResource(R.drawable.stockxsteals) ,
           contentDescription = "App Logo",
           modifier = Modifier
             .fillMaxHeight(.8f)
             .padding())

      Spacer(modifier = Modifier.padding(10.dp))

      RoundTextField(
          navController = navController,
          model = filterModel,
          text = text,
          selected = selected,
          focusManager = focusManager,
          focusRequester = focusRequester,
          keyboardController = keyboardController,
          searchRoute = searchRoute
        )
    }
      Spacer(modifier = Modifier.padding(10.dp))
    }
  }

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RoundTextField(navController: NavHostController,
                   model: FilterViewModel,
                   text: MutableState<String>,
                   selected: String,
                   focusManager: FocusManager,
                   focusRequester: FocusRequester,
                   keyboardController: SoftwareKeyboardController?,
                   searchRoute: String) {

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val sneakersDestination = AppScreens.SneakerSearch.route
  val searchDestination = AppScreens.Search.route
  if (currentDestination?.route == sneakersDestination ||
     currentDestination?.route == searchDestination) text.value = ""
  val coroutineScope = rememberCoroutineScope()

  val mauve = Color(224, 176, 255)
  val search = selected == "Search"
  val search2 = search ||
          currentDestination?.route == "top_search" ||
          currentDestination?.route == sneakersDestination // TODO: Check for weird behaviours

  BasicTextField(
    value = text.value,
    maxLines = 1,
    onValueChange = { text.value = it },
    enabled = true,
    modifier = Modifier
      .focusRequester(focusRequester)
      .onFocusChanged {
        if (it.isFocused) {
          if (search) {
            keyboardController?.show()
            navController.navigate(searchRoute)
          } else if (navController.currentDestination?.route == AppScreens.TopSearch.route) {
            keyboardController?.show()
          } else {
            focusManager.clearFocus()
          }
        }
      }
      .onKeyEvent {
        if (text.value == "") {
          if (it.key == Key.Backspace) {
            navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
            focusManager.clearFocus()
          }
        }
        true
      }
      .height(35.dp)
      .fillMaxWidth(.9f)
      .border(
        border = BorderStroke(
          width = 1.5.dp,
          color =
          if (search2) mauve else Color.Red
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
      trailingIcon = {
        IconButton(onClick = {
          if (search2 && model.searchCheck()) {
            navController.currentBackStackEntry?.savedStateHandle?.set(
              key = "filterModel",
              value = model
            )
            focusManager.clearFocus()
            coroutineScope.launch(Dispatchers.Default) { model.setSearchResults(text.value) }
            navController.navigate(sneakersDestination)
          }

        }) {
          Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon"
          )
        }
      },
      placeholder = {
        Text(
          text = if (search2) "Enter Search ..." else "",
          fontSize = 16.sp,
        )
      },
      interactionSource = interactionSource,
      // keep horizontal paddings but change the vertical
      contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
        top = 0.dp, bottom = 0.dp
      ),
    )
  }
}