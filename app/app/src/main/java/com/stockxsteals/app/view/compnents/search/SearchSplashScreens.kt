package com.stockxsteals.app.view.compnents.search

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.navigation.AppScreens

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController) {
  val filterSelect = remember { mutableStateOf("") }
  val searchWithFilters = SearchWithFilters("", "", "", "", 0.0) //TODO: In future retrieve from ViewModel
  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current

  Scaffold {
    Column(
      modifier = Modifier
        .padding(top = 30.dp)
    ) {

      val selected = remember { mutableStateOf("") }

      Row(modifier = Modifier
        .height(30.dp)
        .padding(start = 15.dp, end = 10.dp)
        .fillMaxWidth()) {

        val chipList = listOf("Country", "Currency", "Size")
        chipList.forEach { it ->
          FilterButtons(button = it,
                        selected = selected.value,
                        filterSelect = filterSelect,
                        onSelected = {
                          selected.value = it
                        })
        }
        SearchPageButtons(navController = navController)
        }
      }
      val codeOrSlug = remember { mutableStateOf("") }

      SearchByChip(codeOrSlug)

      Column(modifier =
      Modifier
        .padding(start = 15.dp, top = 170.dp)
        .height(200.dp)
        .fillMaxWidth(.95f)
        .border(BorderStroke(1.dp, SolidColor(Color.LightGray))),
        ) {
        SwitchFilters(selected = filterSelect.value,
                      filterObj = searchWithFilters,
                      navController = navController,
                      text = remember { mutableStateOf("") },
                      focusManager = focusManager,
                      focusRequester = focusRequester,
                      keyboardController = keyboardController,
                      searchRoute = AppScreens.TopSearch.route)
      }
    }
  }

@Composable
fun SearchByChip(selected: MutableState<String>) {
  Column(modifier = Modifier.padding(top = 70.dp)) {
    Row(
      modifier = Modifier
        .height(30.dp)
        .padding(start = 15.dp, end = 10.dp)
        .fillMaxWidth()
    ) {

      val chipList = listOf("Code", "Slug")

      chipList.forEach { it ->
        FilterButtons(button = it,
          selected = selected.value,
          filterSelect = null,
          onSelected = {
            selected.value = it
          })
      }
    }
    DisplayExampleMessage(selected)
  }
}

@Composable
fun SearchPageButtons(navController: NavHostController) {
  val focusManager = LocalFocusManager.current

  Row(modifier = Modifier.padding(start = 50.dp)) {
    IconButton(
      onClick = {
      navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
      focusManager.clearFocus()
    }) {
      Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "Back Icon"
      )
    }
  }
}


@Composable
fun  FilterButtons(button: String,
                   selected: String,
                   filterSelect: MutableState<String>?,
                   onSelected: (String) -> Unit) {

  val isSelected = selected == button

  val mauve = Color(224, 176, 255)
  val bgColor = if (isSelected) mauve else Color.White
  val textColor = if (isSelected) Color.White else Color.Black
  val fontWeight  = if (isSelected) FontWeight.Bold else FontWeight.Light

  Button(modifier = Modifier
    .width(90.dp)
    .padding(end = 5.dp),
    border = BorderStroke(0.5.dp, color = Color(224, 176, 255)),
    shape = RoundedCornerShape(50.dp),
    colors = ButtonDefaults.buttonColors(
      backgroundColor = bgColor
    ),
    onClick = {
      onSelected(button)
      if (listOf("Country", "Currency", "Size").contains(button)) {
        filterSelect!!.value = button
      }
    }) {

    Text(text = button,
      fontSize = 10.sp,
      fontWeight = fontWeight,
      textAlign = TextAlign.Center,
      color = textColor)
  }
}