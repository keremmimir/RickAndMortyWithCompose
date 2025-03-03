package com.example.rickandmortywithcompose.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rickandmortywithcompose.data.model.CharacterModel
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.screens.detail.DetailScreen
import kotlin.reflect.typeOf

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    bottomBarNavController: NavHostController,
    mainViewModel: MainViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreens.BottomBar
    ) {
        composable<HomeScreens.BottomBar> {
            BottomBarScreen(mainViewModel = mainViewModel, navController = bottomBarNavController)
        }

        composable<HomeScreens.Detail>(
            typeMap = mapOf(typeOf<CharacterModel>() to createGenericNavType<CharacterModel>())
        ) {
            val arguments = it.toRoute<HomeScreens.Detail>()
            DetailScreen(mainViewModel = mainViewModel, arguments = arguments)
        }

    }
}