package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.LightPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchCard(
    onClick: () -> Unit,
    icon: Int
) {
    var text by rememberSaveable { mutableStateOf<String>("") }
    var labelColor = if (!text.isEmpty()) LightPrimary else Color.Gray
    var isClicked by remember { mutableStateOf<Boolean>(false) }
    val coroutineScope = rememberCoroutineScope()
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                label = {
                    Text(
                        "Enter text",
                        color = labelColor
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = LightPrimary,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )
                IconButton(
                    onClick = {
                        onClick.invoke()
                        isClicked = true
                        text = ""

                        coroutineScope.launch {
                            delay(100L)
                            isClicked = false
                        }
                    }

                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "icon",
                        tint =
                            if (isClicked) {
                                LightPrimary
                            } else
                                MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(40.dp)

                    )
                }
            }
        }
}