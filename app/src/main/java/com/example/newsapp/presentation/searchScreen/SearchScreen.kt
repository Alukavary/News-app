package com.example.newsapp.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.presentation.components.CustomIconButton
import com.example.newsapp.presentation.components.SearchCard
import com.example.newsapp.presentation.components.Title
import com.example.newsapp.ui.theme.Grey

@Preview(showBackground = true)
@Composable

fun SearchScreen(
    viewModel: SearchVM = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp, bottom = 70.dp)
        ){
        Row(
            Modifier.padding(bottom = 15.dp)
        ){
            Title("Search")
        }
        SearchCard(onClick = {}, R.drawable.search)

    }

}