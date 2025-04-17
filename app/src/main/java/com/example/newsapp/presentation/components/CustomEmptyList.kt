package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary

@Composable
fun CustomEmptyList(
    icon: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = LightPrimary,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            color = LightPrimary,
            modifier = Modifier
                .padding(top = 20.dp)
        )
    }
}