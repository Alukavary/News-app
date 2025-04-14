package com.example.newsapp.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.navigation.BottomNavBar
import com.example.newsapp.presentation.navigation.NavGraph
import com.example.newsapp.presentation.settingsScreen.SettingsVM
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun MainScreen(
    viewModel: SettingsVM = hiltViewModel()
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRout = backStackEntry?.destination?.route
    val isDark by viewModel.moodType.collectAsState()

    NewsAppTheme(darkTheme = isDark) {
        Scaffold(
            bottomBar = {
                if (currentRout != "$DETAILS_SCREEN_ROUTE/{articleJson}") {
                    BottomNavBar(navController = navController)
                }
            }

        ) {
            NavGraph(navHostController = navController)
        }
    }
}

