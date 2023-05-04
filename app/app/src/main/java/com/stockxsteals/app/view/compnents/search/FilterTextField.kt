package com.stockxsteals.app.view.compnents.search

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
import com.stockxsteals.app.viewmodel.UIViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun FilterTextField(model: FilterViewModel,
                    uiModel: UIViewModel,
                    selected: String,
                    text: MutableState<String>,
                    progressCount: MutableState<Int>,
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
    onValueChange = {
      text.value = it
      if (uiModel.countryFilled(text.value))
        expanded.value = true
    },
    enabled = uiModel.selectedIsCountry(selected),
    modifier = Modifier
      .clickable {
        if (!uiModel.selectedIsCountry(selected))
          expanded.value = !expanded.value
      }
      .focusRequester(focusRequester)
      .onFocusChanged {
        if (it.isFocused) {
          keyboardController?.show()
        }
      }
      .onKeyEvent {
        if (uiModel.textIsEmpty(text.value)) {
          if (uiModel.nextPressBackSpace(it)) {
            focusManager.clearFocus()
          }
        } else if (uiModel.nextPressBackSpace(it)) {
          expanded.value = false
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
          if (!uiModel.selectedIsCountry(selected))
            expanded.value = !expanded.value
          focusManager.clearFocus()
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

      if (uiModel.selectedIsCountry(selected)) {
        countryListToggle(text.value, filterMap).forEach {
          DropdownMenuItem(onClick = {

            if (uiModel.progressCheck(progressCount.value)
              && model.getCurrentSearch().country.isEmpty())  progressCount.value++

            model.appendCountryAndCurrency("Country", it.toString())
            text.value = ""
            label.value = it.toString()
            focusManager.clearFocus()
            expanded.value = !expanded.value

          }) {
           Text(text = it.toString())
          }
        }
      }

      filterMap[selected]?.forEach { it ->
          when (selected) {
            "Currency" -> {
              DropdownMenuItem(onClick = {

                if (uiModel.progressCheck(progressCount.value)
                  && model.getCurrentSearch().currency.isEmpty()) progressCount.value++

                model.appendCountryAndCurrency("Currency", (it as Currency).name)
                label.value = it.type
                expanded.value = !expanded.value

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondaryFilterTextField(model: FilterViewModel,
                             uiModel: UIViewModel,
                    selected: String,
                    text: MutableState<String>,
                    progressCount: MutableState<Int>) {

  val expanded = remember { mutableStateOf(false) }
  val placeholder = remember { mutableStateOf("Your Size") }
  val textFieldSize = remember { mutableStateOf(Size.Zero) }
  val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp
  else Icons.Filled.KeyboardArrowDown
  val mauve = Color(224, 176, 255)

  BasicTextField(
    value = text.value,
    maxLines = 1,
    onValueChange = { text.value = it; },
    enabled = false,
    modifier = Modifier
      .clickable {
        if (model.getCurrentSearch().sizeType.isNotEmpty())
          expanded.value = !expanded.value
      }
      .onGloballyPositioned { coordinates ->
        textFieldSize.value = coordinates.size.toSize()
      }
      .height(35.dp)
      .fillMaxWidth(.8f)
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
          text = if (model.getCurrentSearch().size != 0.0) {

            if (model.getCurrentSearch().toString().contains(".0"))
              model.getCurrentSearch().size.toInt().toString()
            else model.getCurrentSearch().size.toString()

          } else {
            placeholder.value
          },
          fontSize = 14.sp,
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
        "Size" -> {
          ShoeSize.valueOf(model.getCurrentSearch().sizeType).listOfSizes.forEach { size ->
            DropdownMenuItem(onClick = {
              if (model.getCurrentSearch().sizeType.isNotEmpty()) {
                if (uiModel.progressCheck(progressCount.value) &&
                  model.getCurrentSearch().size == 0.0)  progressCount.value++

                model.appendSize(size, null)
                placeholder.value = uiModel.sizeModifier(size)
                expanded.value = !expanded.value
              }
            }) {
              Text(text = uiModel.sizeModifier(size))
            }
          }
        }
      }
    }
  }
}

fun countryListToggle(text: String, filterMap: Map<String, List<Any>>): List<Any> {
  if (text.isNotEmpty()) return filterMap["Country"]!!.filter {
      match -> match.toString().take(1).uppercase()
                    .contains(text.take(1).uppercase())
      }
  return listOf()
}