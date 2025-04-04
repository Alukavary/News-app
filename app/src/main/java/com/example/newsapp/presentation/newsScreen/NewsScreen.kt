package com.example.newsapp.presentation.newsScreen

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.components.CategoryRow
import com.example.newsapp.presentation.components.ErrorScreen
import com.example.newsapp.presentation.components.ListViewForStart
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
        Log.d("MyLog", " in NewsVM until ScreenMain state $state")


        when (state) {
            is UIState.Loading -> LoadingScreen()
            is UIState.Success -> {
                val data: List<ArticleModel> =
                    when (state) {
                        is UIState.Success -> (state as UIState.Success<List<ArticleModel>>).data
                        else -> emptyList()
                    }
                    ScreenMain(
                        navController = navController,
                        viewModel = viewModel,
                        data = data
                    )
            }

            is UIState.Error -> ErrorScreen("Incorrect input try again", context = context)
        }
    }
}

@Composable
fun ScreenMain(
    data: List<ArticleModel>,
    viewModel: NewsVM = hiltViewModel(),
    navController: NavController
) {
    Log.d("MyLog", " in NewsVM until ScreenMain")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp, bottom = 70.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            CategoryRow()
            ListViewForStart(
                data,
                navController,
            )
        }
    }
}
