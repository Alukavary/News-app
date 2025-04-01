package com.example.newsapp.presentation.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.data.models.NewsResponseDto
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.detailsScreen.DetailsScreen

@Composable

fun ListViewForStart(
    data: List<Article>,
    navController: NavController,
) {
    val firstNews = data.first()
    val newData = data.drop(1)
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            NewsViewStart(
                firstNews,
                navController
            )
        }
        items(newData.size) { item ->
            ItemNews(
                newData[item],
                navController,
            )
        }
    }
}
