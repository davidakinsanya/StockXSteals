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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stockxsteals.app.R
import com.stockxsteals.app.navigation.AppScreens


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(navController: NavHostController) {

  val screens = listOf(
    AppScreens.Trends,
    AppScreens.SearchByCode,
    AppScreens.SearchBySlug,
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
    val text = remember { mutableStateOf(selected) }

    Row(modifier = Modifier.padding(start = 10.dp)) {

      Image(painter = painterResource(R.drawable.stockxsteals) ,
           contentDescription = "App Logo",
           modifier = Modifier
             .fillMaxHeight(.8f)
             .padding())

      Spacer(modifier = Modifier.padding(10.dp))

      BasicTextField(
        value = text.value,
        maxLines = 1,
        onValueChange = {
          text.value = it;
           if (text.value == "") {
            navController.popBackStack()
            focusManager.clearFocus()
          }},
        enabled = true,
        modifier = Modifier
          .focusRequester(focusRequester)
          .onFocusChanged {
            if (it.isFocused) {
             keyboardController?.show()
              navController.navigate(searchRoute)
            }
          }
          .onKeyEvent {
            if (text.value == "" && it.key == Key.Backspace) {
              navController.popBackStack()
              focusManager.clearFocus()
            }
            true
          }
          .height(35.dp)
          .fillMaxWidth(.9f)
          .border(
            border = BorderStroke(2.dp, color = Color(224, 176, 255)),
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
            IconButton(onClick = { /*TODO*/ }) {
              Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
              )
            }
          },
          placeholder = {
            Text(
              text = selected,
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

      Spacer(modifier = Modifier.padding(10.dp))

    }
  }
}