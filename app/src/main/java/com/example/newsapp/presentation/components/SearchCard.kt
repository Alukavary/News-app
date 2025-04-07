package com.example.newsapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.newsapp.presentation.newsScreen.NewsVM
import com.example.newsapp.presentation.searchScreen.SearchVM
import com.example.newsapp.ui.theme.LightPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchCard(
    viewModel: SearchVM = hiltViewModel(),
    icon: Int
) {
    var text by rememberSaveable { mutableStateOf<String>("") }
    var labelColor = if (!text.isEmpty()) LightPrimary else Color.Gray
    var isClicked by remember { mutableStateOf<Boolean>(false) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 15.dp)
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
                    viewModel.loadingCategory(text)
                    isClicked = true
                    Log.d("MyLog", "text $text")
                    text = ""
                }
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    tint = LightPrimary,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}