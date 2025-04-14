package com.example.newsapp.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.newsapp.R
import com.example.newsapp.domain.model.ErrorType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.presentation.components.ErrorNetworkWithoutCache
import com.example.newsapp.presentation.components.ErrorScreen
import com.example.newsapp.presentation.components.ListViewForSearch
import com.example.newsapp.presentation.components.LoadingScreen
import com.example.newsapp.presentation.components.SearchCard
import com.example.newsapp.presentation.components.Title

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchVM = hiltViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.data.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 120.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .padding(horizontal = 20.dp)
        ) {
            Title("Search")
        }
        SearchCard(
            icon = R.drawable.search
        )
        Column {
            when (val result = state) {
                is UIState.Loading -> LoadingScreen()
                is UIState.Success -> ListViewForSearch(result.data, navController)
                is UIState.Empty -> {}
                is UIState.Error -> {
                    when (result.type) {
                        ErrorType.NETWORK_WITHOUT_CACHE -> ErrorNetworkWithoutCache()
                        ErrorType.NETWORK_WITH_CACHE -> ListViewForSearch(
                            result.data ?: emptyList(), navController
                        )

                        else -> ErrorScreen("Incorrect input try again", context = context)
                    }
                }
            }
        }
    }
}