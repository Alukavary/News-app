package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleModel

@Composable
fun ListViewForSearch(
    data: List<ArticleModel>?,
    navController: NavController,
) {
    if (data?.isNotEmpty() == true) {
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(data.size) { item ->
                ItemNews(
                    data[item],
                    navController,
                )
            }
        }
    } else{
        CustomEmptyList(
            icon = R.drawable.search_off_24,
            text = "Nothing found")
    }
}
