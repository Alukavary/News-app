package com.example.newsapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.data.models.NewsCategory
import com.example.newsapp.ui.theme.LightPrimary
import kotlin.String
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect


@Composable
fun CategoryRow(
    selectedCategory:String,
    onClick: (String)-> Unit
) {
    val listState = rememberLazyListState()

    val itemList = listOf(
        NewsCategory("Business"),
        NewsCategory("Entertainment"),
        NewsCategory("General"),
        NewsCategory("Health"),
        NewsCategory("Science"),
        NewsCategory("Sports"),
        NewsCategory("Technology"),
    )

    LaunchedEffect(selectedCategory) {
        val index = itemList.indexOfFirst { it.category == selectedCategory}
        if (index >= 0) {
            listState.animateScrollToItem(index)
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
            .padding(top = 35.dp),
        state = listState
    ) {

        items(itemList.size) { index ->
            val item = itemList[index]
            ThemeItem(
                item.category,
                onClick = {
                    if (item.category != selectedCategory) {
                        onClick.invoke(item.category)
                    }
                },
                isSelected = selectedCategory == item.category,
            )
        }
    }
}

@Composable
fun ThemeItem(
    theme: String,
    onClick: () -> Unit,
    isSelected: Boolean,
) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .padding(vertical = 10.dp)
            .clickable(onClick = onClick),
    ) {

        Text(
            text = theme,
            fontSize =
                if (isSelected) {
                    23.sp
                } else {
                    16.sp
                },

            color =
                if (isSelected)
                    LightPrimary
                else
                    MaterialTheme.colorScheme.onBackground,
        )
    }
}