package com.example.newsapp.presentation.navigation

import com.example.newsapp.R
import com.example.newsapp.utils.Constants.FAVORITE_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.MAIN_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.SEARCH_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.SETTINGS_SCREEN_ROUTE

sealed class BottomItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    object MainNewsScreen :
        BottomItem(title = "news", icon = R.drawable.newspaper, route = MAIN_SCREEN_ROUTE)

    object FavoriteScreen :
        BottomItem(title = "favorite", icon = R.drawable.bookmark, route = FAVORITE_SCREEN_ROUTE)

    object SearchScreen :
        BottomItem(title = "search", icon = R.drawable.search, route = SEARCH_SCREEN_ROUTE)

    object SettingsScreen
        : BottomItem(title = "settings", icon = R.drawable.baseline_settings_24, route = SETTINGS_SCREEN_ROUTE)
}