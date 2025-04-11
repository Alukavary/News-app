package com.example.newsapp.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.LightPrimary
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Text(
                text = "Loading...",
                fontSize = 20.sp,
                color = LightPrimary
            )
            CircularProgressIndicator(
                color = LightPrimary,
                modifier = Modifier.padding(10.dp),

                )
        }
    }
}


@Composable
fun ErrorScreen(
    msg: String,
    context: Context
) {
    ShowToast(context, msg)
}


@Composable
fun ShowToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.show()
}

@Preview(showBackground = true)
@Composable
fun ErrorNetworkWithoutCache(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.signal_error),
            contentDescription = "icon",
            tint = LightPrimary,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "No signal, check the internet",
            fontSize = 20.sp,
            color = LightPrimary,
            modifier = Modifier
                .padding(top = 20.dp)
        )
    }
}