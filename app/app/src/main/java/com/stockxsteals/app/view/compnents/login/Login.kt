package com.stockxsteals.app.view.compnents.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.stockxsteals.app.R
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController,
                networkModel: NetworkViewModel) {

  val state = rememberOneTapSignInState()
  val scope = rememberCoroutineScope()
  val context = LocalContext.current
  val mauve = Color(224, 176, 255)

  OneTapSignInWithGoogle(
    state = state,
    clientId = "598526411757-osmt0f7ja2qs6rqrqp5j12s5lkq77quv.apps.googleusercontent.com",
    onTokenIdReceived = { tokenId ->
      Log.d("LOG", tokenId)
      navController.navigate("trends_route")
    },
    onDialogDismissed = { message ->
      Log.d("LOG", message)
    }
  )

  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(
      top = 50.dp,
      start = 80.dp,
      end = 80.dp
    )) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {

      Image(painter = painterResource(R.drawable.stockxsteals),
        contentDescription = "Logo",
        modifier = Modifier
          .fillMaxWidth(0.8f)
          .fillMaxHeight(0.5f))


        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
              .width(300.dp)
              .clickable(
                enabled = !state.opened
              ) {
                scope.launch {
                  if (networkModel.checkConnection(context))
                    state.open()
                  else
                    networkModel.toastMessage(context)
                }
              }
              .border(
                border = BorderStroke(
                  width = 1.2.dp,
                  color = mauve
                ),
                shape = RoundedCornerShape(50.dp)
              )
              .background(color = Color.Transparent),

          ) {
          Row(verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center) {
            AsyncImage(
              model = "https://img.icons8.com/?size=512&id=V5cGWnc9R4xj&format=png",
              contentDescription = "Google Logo",
              modifier = Modifier.fillMaxSize(0.15f).padding(end = 10.dp),
              alignment = Alignment.Center
            )
            Text(text = "Sign In With Google",
              modifier = Modifier.padding(start = 2.dp),
              fontSize = 16.sp, fontWeight = FontWeight.Medium,
              textAlign = TextAlign.Center)
          }
        }
      }
    }
  }