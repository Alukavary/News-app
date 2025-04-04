package com.example.newsapp.presentation.components

import android.content.Context
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.transition.CircularPropagation
import com.example.newsapp.R

//@Preview(showBackground = true)
@Composable
fun LoadingScreen() {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(
            text = "Loading...",
            fontSize = 20.sp,
        )
        CircularProgressIndicator(
            color = Color.Black, modifier = Modifier.padding(10.dp)
        )
    }
}


@Composable
fun ErrorScreen(
    msg: String, context: Context
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.fake_news_icon),
            contentDescription = "icon",
            Modifier.size(150.dp)
        )
    }
    ShowToast(context, msg)

}


@Composable
fun ShowToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
//    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)
    toast.show()
}

@Preview(showBackground = true)
@Composable
fun ErrorNetwork() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.signal_error_internet),
            contentDescription = "icon",
            Modifier.size(150.dp)
        )

    }
}