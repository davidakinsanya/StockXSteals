package com.stockxsteals.app.view.compnents.search

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.stockxsteals.app.model.filter.Currency
import com.stockxsteals.app.model.filter.ShoeSize
import com.stockxsteals.app.viewmodel.FilterViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun FilterTextField(model: FilterViewModel,
                    selected: String,
                    text: MutableState<String>,
                    focusManager: FocusManager,
                    focusRequester: FocusRequester,
                    keyboardController: SoftwareKeyboardController?) {

  val expanded = remember { mutableStateOf(false) }
  val textFieldSize = remember { mutableStateOf(Size.Zero) }
  val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp
  else Icons.Filled.KeyboardArrowDown
  val filterMap = model.getFilterMap()
  val label = remember { mutableStateOf(selected) }
  val mauve = Color(224, 176, 255)

  BasicTextField(
    value = text.value,
    maxLines = 1,
    onValueChange = { text.value = it; },
    enabled = selected == "Country" || selected == "Currency",
    modifier = Modifier
      .focusRequester(focusRequester)
      .onFocusChanged {
        if (it.isFocused) {
          keyboardController?.show()
        }
      }
      .onKeyEvent {
        if (text.value == "") {
          if (it.key == Key.Backspace) {
            focusManager.clearFocus()
          }
        }
        true
      }
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
          text = label.value,
          fontSize = 16.sp,
        )
      },

      trailingIcon = {
        Icon(icon, "", Modifier.clickable {
          expanded.value = !expanded.value
          if (selected == "Country" || selected == "Currency") {
            text.value = ""
            focusManager.clearFocus()
          }
        })
      },
      interactionSource = interactionSource,
      // keep horizontal paddings but change the vertical
      contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
        top = 0.dp, bottom = 0.dp
      ),
    )

    DropdownMenu(
      expanded = expanded.value,
      onDismissRequest = { expanded.value = false },
      modifier = Modifier
        .width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        .height(130.dp)
    ) {

      filterMap[selected]?.forEach { it ->
          when (selected) {
            "Country" -> {
              DropdownMenuItem(onClick = {
                if (text.value.isEmpty()) {
                  model.appendCountryAndCurrency("Country", it.toString())
                  label.value = it.toString()
                  expanded.value = !expanded.value
                }
              }) {
                Text(text = it.toString())
              }
            }
            "Currency" -> {
              DropdownMenuItem(onClick = {
                if (text.value.isEmpty()) {
                  model.appendCountryAndCurrency("Currency", (it as Currency).name)
                  label.value = it.type
                  expanded.value = !expanded.value
                }
              }) {
                Text(text = (it as Currency).type)
              }
            }
            "Size" -> {
              DropdownMenuItem(onClick = {
                model.appendSize(null, (it as ShoeSize).name)
                label.value = it.type
                expanded.value = !expanded.value
              }
              ) {
                Text(text = (it as ShoeSize).type)
              }
            }
          }
      }
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun SecondaryFilterTextField(model: FilterViewModel,
                    selected: String,
                    text: MutableState<String>) {

  val expanded = remember { mutableStateOf(false) }
  val textFieldSize = remember { mutableStateOf(Size.Zero) }
  val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp
  else Icons.Filled.KeyboardArrowDown
  val mauve = Color(224, 176, 255)

  BasicTextField(
    value = text.value,
    maxLines = 1,
    onValueChange = { text.value = it; },
    enabled = selected != "Country",
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
        Icon(icon, "", Modifier.clickable {
          if (model.getCurrentSearch().sizeType.isNotEmpty()) {
            expanded.value = !expanded.value
          }
        })
      },
      interactionSource = interactionSource,
      // keep horizontal paddings but change the vertical
      contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
        top = 0.dp, bottom = 0.dp
      ),
    )

    DropdownMenu(
      expanded = expanded.value,
      onDismissRequest = { expanded.value = false },
      modifier = Modifier
        .width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        .height(130.dp)
    ) {

      when (selected) {
        "Currency" -> {
          model.getCurrencyType().forEach { currency ->
            DropdownMenuItem(onClick = { /*TODO*/ }) {
              Text(text = currency.symbol)
            }
          }
        }
        "Size" -> {
          ShoeSize.valueOf(model.getCurrentSearch().sizeType).listOfSizes.forEach { size ->
            DropdownMenuItem(onClick = {
              if (model.getCurrentSearch().sizeType.isNotEmpty()) {
                model.appendSize(size, null)
              }
            }) {
              Text(text = size.toString())
            }
          }
        }
      }
    }
  }
}