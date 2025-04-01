package com.example.newsapp.presentation.newsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.components.CategoryRow
import com.example.newsapp.presentation.components.ListViewForStart
import com.example.newsapp.presentation.components.LoadingScreen
import com.example.newsapp.ui.theme.LightPrimary


@Preview(showBackground = true)
@Composable

fun MainNewsScreen(
    viewModel: NewsVM = hiltViewModel(),
    navController: NavController

){
    val data = remember { mutableStateOf<List<Article>?>(null) }
    LaunchedEffect(Unit) {
        viewModel.data.observeForever { news->
            data.value = news
        }
    }

    data.value?.let { news ->
            ScreenMain(
                news,
                viewModel,
                navController
            )
        } ?: run {
        LoadingScreen()
    }

}

@Composable
fun ScreenMain(
    data: List<Article>,
    viewModel: NewsVM = hiltViewModel(),
    navController: NavController
){
//    val insets = WindowInsets.systemBars.asPaddingValues()
    var isClicked by remember { mutableStateOf(false) }

    Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(insets)
                .padding(horizontal = 20.dp)
                .padding(top = 50.dp, bottom = 70.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .clickable{
                        viewModel.getDataHeadLines()
                    isClicked = true
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.media0_news_online_icon),
                    contentDescription = "icon",
                    tint = LightPrimary,
                    modifier = Modifier
                        .size(80.dp),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                CategoryRow(
                )
                ListViewForStart(
                    data,
                    navController,
                )
            }
    }

}
