package com.example.newsapp.presentation.navigation

import android.net.http.SslCertificate.saveState
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.ui.theme.LightBackground
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightPrimary
import com.example.newsapp.ui.theme.LightText
import com.example.newsapp.utils.Constants.DETAILS_SCREEN_ROUTE


@Composable
fun BottomNavBar(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.MainNewsScreen,
        BottomItem.FavoriteScreen,
        BottomItem.SearchScreen,
        BottomItem.SettingsScreen
    )
    NavigationBar(
        modifier = Modifier
            .background(LightBackground)
            .height(120.dp)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRout == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        tint =
                            if (currentRout == item.route)
                                LightPrimary
                            else
                                MaterialTheme.colorScheme.onBackground
                    )

                },
                label = {
                    if (currentRout == item.route) {
                        Text(
                            text = item.title,
                            fontSize = 13.sp,
                        )
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LightPrimary,
                    unselectedIconColor = LightText,
                    unselectedTextColor = LightText,
                    selectedTextColor = LightPrimary
                )
            )
        }
    }

}

