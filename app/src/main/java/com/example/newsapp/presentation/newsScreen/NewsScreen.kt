package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.categoryScreen.CategoryRow
import com.example.newsapp.presentation.components.ErrorScreen
import com.example.newsapp.presentation.components.LazyColumForNews
import com.example.newsapp.presentation.components.LoadingScreen


@Preview(showBackground = true)
@Composable

fun MainNewsScreen(
    viewModel: NewsVM = hiltViewModel(),
    navController: NavController
) {
    Log.d("MyLog", " in NewsVM until MainNewsScreen")

    val context = LocalContext.current
    val category by viewModel.selectedCategory.collectAsState()

    category?.let { category ->
        val state by viewModel.data.collectAsState()
        val generateList by viewModel.generateItemList.collectAsState()
        Log.d("MyLog", " in NewsVM until ScreenMain state $state")


        when (state) {
            is UIState.Loading -> LoadingScreen()
            is UIState.Success -> {ScreenMain(
                        navController = navController,
                        data = generateList,
                    )
            }

            is UIState.Error -> ErrorScreen("Incorrect input try again", context = context)
        }
    }
}

@Composable
fun ScreenMain(
    data: List<GenerateItem>,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 70.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
//            CategoryRow()
            LazyColumForNews(
                data = data,
                navController = navController
            )
        }
    }
}
