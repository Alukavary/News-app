package com.example.newsapp.presentation.newsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.components.ErrorScreen
import com.example.newsapp.presentation.components.LazyColumForNews
import com.example.newsapp.presentation.components.LoadingScreen
import com.example.newsapp.presentation.components.PullRefresh

@Composable

fun MainNewsScreen(
    viewModel: NewsVM = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val category by viewModel.selectedCategory.collectAsState()

    category?.let { category ->
        val state by viewModel.data.collectAsState()
        val generateList by viewModel.generateItemList.collectAsState()
        when (state) {
            is UIState.Loading -> LoadingScreen()
            is UIState.Success -> {
                ScreenMain(
                    navController = navController,
                    data = generateList,
                )
            }
            is UIState.Error -> {ErrorScreen("Incorrect input try again", context = context) }
            is UIState.Default -> {}
        }
    }
}

@Composable
fun ScreenMain(
    data: List<GenerateItem>,
    navController: NavController,
    viewModel: NewsVM = hiltViewModel(),
) {
    val context = LocalContext.current
    val category by viewModel.selectedCategory.collectAsState()
    val isRefresh by viewModel.isRefresh.collectAsState()
    val state by viewModel.data.collectAsState()
    val generateList by viewModel.generateItemList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 70.dp),
    ) {
        category?.let { category ->
            PullRefresh(
                isRefreshing = isRefresh,
                onRefresh = { viewModel.loadingCategory(category) }
            ) {
                when (state) {
                    is UIState.Loading -> LoadingScreen()
                    is UIState.Success -> {
                        LazyColumForNews(
                            navController = navController,
                            data = generateList,
                        )
                    }
                    is UIState.Error -> {ErrorScreen("Incorrect input try again", context = context) }
                    is UIState.Default -> {}
                }
            }
        }
    }
}
