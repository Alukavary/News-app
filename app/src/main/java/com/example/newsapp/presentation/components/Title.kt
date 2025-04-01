package com.example.newsapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary


@Composable
fun Title(title: String) {
    Text(
        text = title,
        fontSize = 40.sp,
        color = LightPrimary,
        fontWeight = FontWeight.Bold
    )

}