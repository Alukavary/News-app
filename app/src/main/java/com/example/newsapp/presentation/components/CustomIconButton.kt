package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.LightPrimary

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    icon: Int,
) {
    var isClicked by remember { mutableStateOf<Boolean>(false) }

    IconButton(
        onClick = {
            onClick.invoke()
            isClicked = true
        }

    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = LightPrimary,
            modifier = Modifier.size(50.dp)

        )
    }
}