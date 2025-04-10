package com.example.newsapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.presentation.newsScreen.NewsVM

@Composable
fun LazyColumForNews(
    data: List<ArticleModel>?,
    navController: NavController,
    viewModel: NewsVM = hiltViewModel()
) {

    val generateList = viewModel.generateItem(data)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Log.d("MyLog", " LazyColumForNews ${generateList.size}")

        for (i in generateList.indices) {
            when (generateList[i].type) {
                ItemType.TYPE1 -> {
                    item {
                        ItemFavorite(
                            item = generateList[i].item, navController = navController
                        )
                    }
                }
                else -> {
                    item {
                        ItemNews(
                            item = generateList[i].item, navController = navController
                        )
                    }
                }
            }
        }
    }
}
