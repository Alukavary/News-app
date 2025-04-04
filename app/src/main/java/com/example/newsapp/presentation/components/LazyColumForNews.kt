package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.presentation.categoryScreen.CategoryRow
import com.example.newsapp.utils.Constants.MAIN_SCREEN_ROUTE

@Composable
fun LazyColumForNews(
    data: List<GenerateItem>,
    navController: NavController
) {
//    CategoryRow()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {

        for (i in data.indices) {

            when (data[i].type) {
                ItemType.TYPE1 -> {
                    item {
                        ItemFavorite(
                            item = data[i].item, navController = navController
                        )
                    }
                }

                else -> {
                    item {
                        ItemNews(
                            item = data[i].item, navController = navController
                        )
                    }
                }
            }
        }
    }
}
