package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.components.CategoryRow
import com.example.newsapp.presentation.navigation.BottomNavBar
import com.example.newsapp.presentation.navigation.NavGraph
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE
import com.example.newsapp.utils.Constants.MAIN_SCREEN_ROUTE


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun MainScreen() {
    Log.d("MyLog", " in  MainScreen")
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRout = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRout != "$DETAILS_SCREEN_ROUTE/{articleJson}") {
                BottomNavBar(navController = navController)
            }
        },
                topBar = {
            if (currentRout == MAIN_SCREEN_ROUTE) {
                CategoryRow()
            }
        }

    ) {
        NavGraph(navHostController = navController)
    }
}
