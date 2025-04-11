package com.example.newsapp.presentation.favoriteScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.presentation.components.CustomIconButton
import com.example.newsapp.presentation.components.ItemFavorite
import com.example.newsapp.presentation.components.Title

@Composable
fun FavoriteScreen(
    viewModel: FavoriteVM = hiltViewModel(),
    navController: NavController,
) {
    val data by viewModel.favoriteArticle.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 50.dp, bottom = 70.dp)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Title("Favorite")
            CustomIconButton(
                onClick = { viewModel.allFavDelete(viewModel.localdb) },
                icon = R.drawable.baseline_restore_from_trash_24
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(data.size) { item ->
                ItemFavorite(
                    data[item], navController = navController
                )
            }
        }
    }
}

