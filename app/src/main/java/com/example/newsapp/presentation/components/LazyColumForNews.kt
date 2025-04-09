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
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.presentation.newsScreen.NewsVM

@Composable
fun LazyColumForNews(
    navController: NavController,
    data: List<GenerateItem>,
    ) {


        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Log.d("MyLog", " LazyColumForNews ${data.size}")

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
