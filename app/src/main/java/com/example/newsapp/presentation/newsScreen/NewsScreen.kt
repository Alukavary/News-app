package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ErrorType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.components.ErrorNetwork
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
        val state by viewModel.data.collectAsStateWithLifecycle()
        Log.d("MyLog", "сколько даты в ньюс скрин пришло со стейт ${state}")

        when (val result = state) {
            is UIState.Loading -> LoadingScreen()
            is UIState.Default -> {}
            is UIState.Success -> {
                ScreenMain(
                    navController = navController,
                    data = result.data,
                    )
            }

            is UIState.Error -> {
                when (result.type) {
                    ErrorType.NETWORK_WITH_CACHE -> ScreenMain(
                        navController,
                        data = result.data,
                        )

                    ErrorType.NETWORK_WITHOUT_CACHE -> ErrorNetwork()
                    ErrorType.OTHER_WITH_CACHE -> ScreenMain(
                        navController,
                        data = result.data,
                    )

                    else -> ErrorScreen(
                        result.msg,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenMain(
    navController: NavController,
    viewModel: NewsVM = hiltViewModel(),
    data: List<ArticleModel>?,
) {
    val category by viewModel.selectedCategory.collectAsState()
    val isRefresh by viewModel.isRefresh.collectAsState()

    val generateItem = viewModel.generateItem(data)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 70.dp),
    ) {
        category?.let { category ->
            PullRefresh(
                isRefreshing = isRefresh, onRefresh = { viewModel.loadingCategory(category) }) {
                LazyColumForNews(
                    navController = navController,
                    data = generateItem
                )
            }
        }
    }
}
