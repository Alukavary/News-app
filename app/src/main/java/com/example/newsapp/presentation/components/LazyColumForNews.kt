package com.example.newsapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

 @Composable
 class LazyColumForNews(
    map: Map<String,
            List<GenerateItem>>,
    navController: NavController
) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        Log.d("MyLog", " в лэзи колум ${map.size}")

        map.forEach { (key, list) ->
            items(list.size) { generateItem ->
                when (list[generateItem].type) {
                    ItemType.TYPE1 -> {
                        ItemFavorite(
                            item = list[generateItem].item, navController = navController
                        )
                    }

                    else -> {
                        ItemNews(
                            item = list[generateItem].item, navController = navController
                        )
                    }
                }
            }
        }
    }

}