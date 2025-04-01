package com.example.newsapp.presentation.favoriteScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.components.CustomIconButton
import com.example.newsapp.presentation.components.ItemFavorite
import com.example.newsapp.presentation.components.ItemNews
import com.example.newsapp.presentation.components.Title
import com.example.newsapp.ui.theme.Grey
import com.example.newsapp.ui.theme.LightPrimary

@Preview(showBackground = true)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteVM = hiltViewModel(),
    navController: NavController
) {
    val insets = WindowInsets.systemBars.asPaddingValues()

    val data by viewModel.favoriteArticle.collectAsState()

//    LaunchedEffect(Unit) {
//        viewModel.data.observeForever { news->
//            data =news
//        }
//    }

    Column(
        modifier = Modifier
            .padding(insets)
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp, bottom = 70.dp)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Title("Favorite")

            Icon(
                painter = painterResource(id = R.drawable.bookmark_favorite),
                contentDescription = null,
                tint = LightPrimary
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(data.size){item ->
//                Row(
//                    modifier =
//                        Modifier
//                            .fillMaxWidth()
//                            .clickable(onClick = {
//                               viewModel.deleteArticles(data.value[item])
//                            })
//
//                ) {
                ItemFavorite(
                    data[item],
                    navController = navController
                )
                }
        }

    }
}

