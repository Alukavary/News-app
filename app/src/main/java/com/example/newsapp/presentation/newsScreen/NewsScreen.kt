package com.example.newsapp.presentation.newsScreen

import android.widget.Toast
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
import androidx.navigation.NavController
import com.example.newsapp.domain.model.ErrorType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.components.CategoryRow
import com.example.newsapp.presentation.components.ErrorNetworkWithoutCache
import com.example.newsapp.presentation.components.ErrorScreen
import com.example.newsapp.presentation.components.LazyColumForNews
import com.example.newsapp.presentation.components.LoadingScreen
import com.example.newsapp.presentation.components.PullRefresh

@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsVM = hiltViewModel(),
) {
    val context = LocalContext.current
    val category by viewModel.selectedCategory.collectAsState()
    val isRefresh by viewModel.isRefresh.collectAsState()
    val state by viewModel.data.collectAsState()

    PullRefresh(
        isRefreshing = isRefresh,
        onRefresh = { viewModel.refreshData(category.toString()) }
    ) {
        CategoryRow(
            selectedCategory = category,
            onClick = { newCategory ->
                viewModel.loadingCategory(newCategory) },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 120.dp),
        ) {

            when (val result = state) {
                is UIState.Loading -> LoadingScreen()
                is UIState.Success -> LazyColumForNews(result.data, navController)
                is UIState.Empty -> {}
                is UIState.Error -> {
                    when (result.type) {
                        ErrorType.NETWORK_WITHOUT_CACHE -> ErrorNetworkWithoutCache()
                        ErrorType.NETWORK_WITH_CACHE -> LazyColumForNews(
                            result.data ?: emptyList(), navController
                        )
                        else -> ErrorScreen("Incorrect input try again", context = context)
                    }
                }
            }
        }
    }
}


