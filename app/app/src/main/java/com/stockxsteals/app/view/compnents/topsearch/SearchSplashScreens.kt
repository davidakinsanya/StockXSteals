package com.stockxsteals.app.view.compnents.topsearch

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
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController,
                 filterModel: FilterViewModel,
                 uiModel: UIViewModel) {

  val filterSelect = remember { mutableStateOf("") }
  val progressCount = remember { mutableStateOf(0) }
  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current

  Scaffold {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)
    ) {
      val selected = remember { mutableStateOf("") }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(30.dp)
          .padding(start = 15.dp, end = 10.dp)
      ) {

        uiModel.listOfChips().forEach { it ->
          FilterButtons(button = it,
            selected = selected.value,
            uiModel = uiModel,
            filterSelect = filterSelect,
            onSelected = { selected.value = it })
        }
        SearchPageButtons(navController = navController, uiModel = uiModel)
      }
    }

    Column(
      modifier =
      Modifier
        .fillMaxWidth(.95f)
        .padding(start = 15.dp, top = 100.dp)
        .height(200.dp)
        .border(BorderStroke(0.5.dp, SolidColor(Color.LightGray))),
    ) {
      Column(
        modifier =
        Modifier
          .fillMaxWidth()
          .height(50.dp)
      ) {
        SwitchFilters(
          filterModel = filterModel,
          uiModel = uiModel,
          selected = filterSelect.value,
          text = remember { mutableStateOf("") },
          progressCount = progressCount,
          focusManager = focusManager,
          focusRequester = focusRequester,
          keyboardController = keyboardController
        )
      }
      CustomProgressBar(progressNum = progressCount.value)
    }

    Column(modifier =
    Modifier
      .fillMaxWidth(.95f)
      .padding(start = 15.dp, top = 310.dp)
      .fillMaxHeight(0.9f)
      .border(BorderStroke(0.5.dp, SolidColor(Color.LightGray))),
    ) {
    }
  }
}

@Composable
fun SearchPageButtons(navController: NavHostController, uiModel: UIViewModel) {
  val searchDestination = AppScreens.Search.route
  val focusManager = LocalFocusManager.current

  Row(modifier = Modifier.padding(start = 30.dp)) {
    IconButton(
      onClick = {
        if (uiModel.previousScreenSneaker(navController)) {
          navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
        } else {
          navController.navigate(searchDestination)
        }
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
                   uiModel: UIViewModel,
                   filterSelect: MutableState<String>?,
                   onSelected: (String) -> Unit) {

  val isSelected = selected == button
  val focusManager = LocalFocusManager.current

  val mauve = Color(224, 176, 255)
  val bgColor = if (isSelected) mauve else Color.White
  val textColor = if (isSelected) Color.White else Color.Black
  val fontWeight  = if (isSelected) FontWeight.Bold else FontWeight.Light

  Button(modifier = Modifier
    .width(90.dp)
    .padding(end = 5.dp),
    border = BorderStroke(0.7.dp, color = Color(224, 176, 255)),
    shape = RoundedCornerShape(50.dp),
    colors = ButtonDefaults.buttonColors(
      backgroundColor = bgColor
    ),
    onClick = {
      focusManager.clearFocus()
      onSelected(button)
      if (uiModel.listOfChips().contains(button)) {
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