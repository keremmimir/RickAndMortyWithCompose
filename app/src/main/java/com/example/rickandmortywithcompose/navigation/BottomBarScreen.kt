package com.example.rickandmortywithcompose.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import kotlin.reflect.KClass

@Composable
fun BottomBarScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentDestinationRoute = currentDestination?.route,
                onHomeBottomBarItemSelected = {
                    navigateWithinBottomBar(navController, HomeScreens.BottomBar.Home::class)
                },
                onFavoriteActionButtonSelected = {
                    navigateWithinBottomBar(navController, HomeScreens.BottomBar.Favorite::class)
                },
            )
        }
    ) {
        BottomBarNavGraph(
            bottomNavController = navController,
            mainViewModel = mainViewModel,
            paddingValues = it
        )
    }
}

fun navigateWithinBottomBar(navController: NavHostController, route: KClass<*>) {
    navController.navigate(route.qualifiedName!!) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}