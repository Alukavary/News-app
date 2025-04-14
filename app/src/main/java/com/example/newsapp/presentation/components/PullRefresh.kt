package com.example.newsapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.ui.theme.LightPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = state,
        modifier = Modifier.fillMaxWidth(),
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state,
                isRefreshing = isRefreshing,
                color = LightPrimary,
            )
        }
    ) {
        content()
    }
}