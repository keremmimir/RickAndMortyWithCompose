package com.example.rickandmortywithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel

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

//        composable<HomeScreens.Upload>(
//            typeMap = mapOf(typeOf<HomeComponentItemDetails>() to createGenericNavType<HomeComponentItemDetails>())
//        ) {
//            val arguments = it.toRoute<HomeScreens.Upload>()
//            UploadScreen(homeViewModel, arguments = arguments)
//        }

    }
}