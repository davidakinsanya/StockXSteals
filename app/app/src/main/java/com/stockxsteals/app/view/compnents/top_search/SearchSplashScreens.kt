package com.stockxsteals.app.view.compnents.top_search

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.FilterViewModel
import com.stockxsteals.app.viewmodel.ui.ProductSearchViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel
import db.entity.FilterPreset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController,
                 productModel: ProductSearchViewModel,
                 windowSize: WindowSize
) {

  val filterSelect = remember { mutableStateOf("") }
  val progressCount = remember { mutableStateOf(0) }
  val focusManager = LocalFocusManager.current
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current

  val model = productModel.getFilterModel().getPresetsModel()
  val allPresets = model.allPreset.collectAsState(initial = emptyList()).value
  val context = LocalContext.current
  val scope = rememberCoroutineScope()

  Scaffold {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)
    ) {
      val selected = remember { mutableStateOf("") }
      Row(
        modifier = Modifier
          .fillMaxWidth(0.9f)
          .height(30.dp)
          .padding(start = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row {
          productModel.getUIModel().listOfChips().forEach { it ->
            FilterButtons(button = it,
              selected = selected.value,
              windowSize = windowSize,
              uiModel = productModel.getUIModel(),
              filterSelect = filterSelect,
              onSelected = { selected.value = it })
          }
        }
        SearchPageButtons(navController = navController,
                          productModel = productModel,
                          windowSize = windowSize)
      }
    }

    Column(
      modifier =
      Modifier
        .fillMaxWidth(.95f)
        .padding(start = 15.dp, top = 100.dp)
        .height(200.dp)
        .border(BorderStroke(1.dp, SolidColor(Color.LightGray))),
    ) {
      Column(
        modifier =
        Modifier
          .fillMaxWidth()
          .height(50.dp)
      ) {
        SwitchFilters(
          productModel = productModel,
          windowSize = windowSize,
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

    LazyColumn(modifier =
    Modifier
      .fillMaxWidth(.95f)
      .padding(start = 15.dp, top = 310.dp)
      .fillMaxHeight(0.9f)
      .border(BorderStroke(1.dp, SolidColor(Color.LightGray))),
    ) {
      items(allPresets.size) { index ->
        DisplayPreset(
          preset = allPresets[index],
          model = productModel.getFilterModel(),
          count = progressCount,
          scope = scope,
          context = context)
      }
    }
  }
}

@Composable
fun SearchPageButtons(navController: NavHostController,
                      productModel: ProductSearchViewModel,
                      windowSize: WindowSize) {
  val uiModel = productModel.getUIModel()
  val searchDestination = AppScreens.Search.route
  val focusManager = LocalFocusManager.current

  Row(modifier = Modifier.padding(start = uiModel.backButtonStartPadding(windowSize))) {
    IconButton(
      onClick = {
        productModel.clearProduct()
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
                   windowSize: WindowSize,
                   filterSelect: MutableState<String>?,
                   onSelected: (String) -> Unit) {

  val isSelected = selected == button
  val focusManager = LocalFocusManager.current

  val mauve = Color(224, 176, 255)
  val bgColor = if (isSelected) mauve else Color.White
  val textColor = if (isSelected) Color.White else Color.Black
  val fontWeight  = if (isSelected) FontWeight.Bold else FontWeight.Light

  Button(modifier = Modifier
    .width(uiModel.filterButtonWidthSmall(windowSize))
    .padding(end = uiModel.filterButtonSpaceBetweenLarge(windowSize)),
    border = BorderStroke(1.dp, color = Color(224, 176, 255)),
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
      fontSize = uiModel.filterButtonTextWidthSmall(windowSize),
      fontWeight = fontWeight,
      textAlign = TextAlign.Center,
      color = textColor)
  }
}

@Composable
fun DisplayPreset(preset: FilterPreset,
                  model: FilterViewModel,
                  count: MutableState<Int>,
                  scope: CoroutineScope,
                  context: Context
) {
  val BLUE = Color(173, 216, 230)
  Row(modifier = Modifier
    .padding(start = 15.dp, end = 30.dp, top = 30.dp)
    .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {

    IconButton(onClick = {
      scope.launch { model.getPresetsModel().deletePreset(preset.id) }
    }) {
      Icon(
        imageVector = Icons.Filled.Delete,
        contentDescription = "Delete Icon",
        modifier = Modifier
          .fillMaxWidth(0.05f)
          .fillMaxHeight(0.05f)
      )
    }
    Row (modifier = Modifier
      .padding(start = 10.dp)
      .fillMaxWidth(0.75f),
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically) {
      Spacer(Modifier.padding(start = 5.dp)) 
      Text("'${preset.country}'", color = BLUE, fontWeight = FontWeight.Bold)
      Text("'${preset.currency}'", color = BLUE, fontWeight = FontWeight.Bold)
      Text("'${preset.sizeType}'", color = BLUE, fontWeight = FontWeight.Bold)
      Text("'${preset.size}'", color = BLUE, fontWeight = FontWeight.Bold)

    }

    IconButton(modifier =
    Modifier
      .padding(start = 30.dp)
      .fillMaxSize(),
      onClick = {
        model.addPreset(preset, count)
        Toast.makeText(context, "Preset Added!", Toast.LENGTH_SHORT).show()
      }) {
      Icon(
        imageVector = Icons.Filled.Check,
        contentDescription = "Check Icon"
      )
    }
  }
}