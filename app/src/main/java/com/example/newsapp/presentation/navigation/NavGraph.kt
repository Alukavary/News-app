package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.presentation.detailsScreen.DetailsScreen
import com.example.newsapp.presentation.favoriteScreen.FavoriteScreen
import com.example.newsapp.presentation.newsScreen.MainNewsScreen
import com.example.newsapp.presentation.searchScreen.SearchScreen
import com.example.newsapp.presentation.settingsScreen.SettingsScreen
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.MAIN_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.FAVORITE_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.SEARCH_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.SETTINGS_SCREEN_ROUTE
import kotlinx.serialization.json.Json


@Composable
fun NavGraph(
    navHostController: NavHostController

) {
    NavHost(navController = navHostController, startDestination = MAIN_SCREEN_ROUTE) {
        composable(MAIN_SCREEN_ROUTE) {
            MainNewsScreen(navController = navHostController)
        }
        composable(FAVORITE_SCREEN_ROUTE) {
            FavoriteScreen(navController = navHostController)
        }
        composable(SEARCH_SCREEN_ROUTE) {
            SearchScreen(navController = navHostController)
        }
//        composable(SETTINGS_SCREEN_ROUTE) {
//            SettingsScreen()
//        }
        composable("${DETAILS_SCREEN_ROUTE}/{articleJson}") { backStackEntry ->
            val json = backStackEntry.arguments?.getString("articleJson")
            val article = Json.decodeFromString<ArticleModel>(json!!)
            DetailsScreen(article, navController = navHostController)
        }
    }
}