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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.data.models.NewsCategory
import com.example.newsapp.presentation.newsScreen.NewsVM
import com.example.newsapp.ui.theme.LightPrimary
import kotlin.String

@Composable
fun CategoryRow(
    viewModel: NewsVM = hiltViewModel(),
) {
    var selectedItemId by rememberSaveable { mutableStateOf<String>("general") }


    val itemList = listOf(
        NewsCategory("Business"),
        NewsCategory("Entertainment"),
        NewsCategory("General"),
        NewsCategory("Health"),
        NewsCategory("Science"),
        NewsCategory("Sports"),
        NewsCategory("Technology"),
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)

        ) {

        items (itemList.size){ item ->
            ThemeItem(
                itemList[item].category,
                onClick = {
                    selectedItemId = itemList[item].category
                    viewModel.loadingCategory(selectedItemId)
                },
                    isSelected = selectedItemId == itemList[item].category,
            )
        }
    }
}

@Composable
fun ThemeItem(
    theme: String,
    onClick:() -> Unit,
    isSelected: Boolean,
//    isSelectedNews: Boolean
) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .padding(vertical = 10.dp)
            .clickable(onClick = onClick),
    ){

        Text(
            text = theme,
            fontSize =
                if(isSelected) {
                    23.sp
                }else {
                    16.sp
            },

            color =
                if(isSelected)
                    LightPrimary
                else
                    MaterialTheme.colorScheme.onBackground,
        )
    }

}

