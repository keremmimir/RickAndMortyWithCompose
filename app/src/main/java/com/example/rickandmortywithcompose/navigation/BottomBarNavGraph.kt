package com.example.rickandmortywithcompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.screens.favorite.FavoriteScreen
import com.example.rickandmortywithcompose.ui.screens.home.HomeScreen

@Composable
fun BottomBarNavGraph(
    bottomNavController: NavHostController,
    mainViewModel: MainViewModel,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = bottomNavController,
        startDestination = HomeScreens.BottomBar.Home
    ) {
        composable<HomeScreens.BottomBar.Home> {
            HomeScreen(mainViewModel = mainViewModel, paddingValues = paddingValues)
        }
        composable<HomeScreens.BottomBar.Favorite> {
            FavoriteScreen(mainViewModel = mainViewModel, paddingValues = paddingValues)
        }
    }
}