package com.stockxsteals.app.view.compnents.main

import android.widget.Toast
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
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
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
import com.stockxsteals.app.ui_coroutines.SearchComposableDB
import com.stockxsteals.app.ui_coroutines.SearchCoroutineOnClick
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(navController: NavHostController,
                 productModel: ProductSearchViewModel,
                 trendsModel: TrendsUIViewModel,
                 networkModel: NetworkViewModel
) {

  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val uiModel = productModel.getUIModel()
  var selected = ""

  uiModel.listOfScreens().forEach { screen ->
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

      RoundTextField(
          navController = navController,
          productModel = productModel,
          trendsModel = trendsModel,
          networkModel = networkModel,
          text = text,
          selected = selected,
          focusManager = focusManager,
          focusRequester = focusRequester,
          keyboardController = keyboardController
        )
    }
      Spacer(modifier = Modifier.padding(10.dp))
    }
  }

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RoundTextField(navController: NavHostController,
                   productModel: ProductSearchViewModel,
                   trendsModel: TrendsUIViewModel,
                   networkModel: NetworkViewModel,
                   text: MutableState<String>,
                   selected: String,
                   focusManager: FocusManager,
                   focusRequester: FocusRequester,
                   keyboardController: SoftwareKeyboardController?) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val sneakersDestination = AppScreens.SneakerSearch.route
    val uiModel =  productModel.getUIModel()
    if (uiModel.resetTextField(currentDestination)) {
      focusManager.clearFocus()
      text.value = ""
    }

    val presetModel = productModel.getFilterModel().getPresetsModel()
    val currentSearch = productModel.getFilterModel().getCurrentSearch()
    val allPresets = presetModel.allPreset.collectAsState(initial = emptyList()).value
    val produceSearch = remember { mutableStateOf(false) }
    val clicked = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    if (navController.previousBackStackEntry?.destination?.route == AppScreens.SneakerSearch.route ) {
      clicked.value = false
      produceSearch.value = false
    }


    val mauve = Color(224, 176, 255)
    val selectedIsSearch = uiModel.selectedIsSearch(selected)
    val purpleSearchBar = uiModel.purpleSearchBar(currentDestination)

    val context = LocalContext.current

    BasicTextField(
      value = text.value,
      maxLines = 1,
      onValueChange = {
        text.value = it
        if (text.value.isEmpty())
          scope.launch {
            trendsModel.resetTrends()
            focusManager.clearFocus()
          }
      },
      enabled = true,
      modifier = Modifier
        .focusRequester(focusRequester)
        .onFocusChanged {
          if (it.isFocused) {
            if (selectedIsSearch) {
              keyboardController?.show()
              navController.navigate(AppScreens.TopSearch.route)
            } else if (navController.currentDestination?.route == AppScreens.TopSearch.route ||
              navController.currentDestination?.route == AppScreens.Trends.route
            ) {
              keyboardController?.show()
            } else {
              focusManager.clearFocus()
            }
          }
        }
        .onKeyEvent {
          if (uiModel.textIsEmpty(text.value)) {
            if (uiModel
                .nextPressBackSpace(it)
              &&
              navController.currentDestination?.route == AppScreens.TopSearch.route
            ) {
              if (navController.previousBackStackEntry?.destination?.route == sneakersDestination) {
                navController.navigate(AppScreens.Search.route)
                focusManager.clearFocus()
              } else {
                navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
                focusManager.clearFocus()
              }
            } else if (uiModel.nextPressBackSpace(it)
              &&
              navController.currentDestination?.route == AppScreens.Trends.route
            ) {
              focusManager.clearFocus()
            }
            productModel.clearProduct()
          }
          true
        }
        .height(35.dp)
        .fillMaxWidth(.9f)
        .border(
          border = BorderStroke(
            width = 1.5.dp,
            color =
            if (purpleSearchBar) mauve else Color.Red
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
            if (uiModel.selectedIsTrend(selected))
              scope.launch {
                trendsModel.filterTrends(text.value)
                focusManager.clearFocus()
              }

            if (purpleSearchBar && !uiModel.selectedIsTrend(selected)) {
              if (uiModel.selectedIsSearch(selected)) {
                navController.navigate(AppScreens.TopSearch.route)
              } else if (
                productModel.getFilterModel()
                  .searchCheck() && text.value.isNotEmpty()
              ) {
                clicked.value = true
                focusManager.clearFocus()
              } else if (productModel.getFilterModel()
                  .searchCheck() || text.value.isEmpty()
              )
                if (navController.currentDestination?.route == sneakersDestination) {
                  Toast.makeText(context, "Please select a sneaker.", Toast.LENGTH_SHORT).show()
                } else if (!productModel.getFilterModel().searchCheck()) {
                  Toast.makeText(context, "Please complete all filters.", Toast.LENGTH_SHORT).show()
                } else {
                  Toast.makeText(context, "Please enter your sneaker.", Toast.LENGTH_SHORT).show()
                }
            }
          })
          {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = "Search Icon"
            )
          }
        },
        placeholder = {
          Text(
            text = if (purpleSearchBar) {
              "Enter Search ..."
            } else "",
            fontSize = 16.sp,
          )
        },
        interactionSource = interactionSource,
        // keep horizontal paddings but change the vertical
        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
          top = 0.dp, bottom = 0.dp
        ),
      )

      if (clicked.value)
        SearchCoroutineOnClick(
          networkModel, context, presetModel,
          allPresets, currentSearch, produceSearch
        )

      SearchComposableDB(
        produceSearch, productModel,
        navController, sneakersDestination, text.value
      )
    }
  }