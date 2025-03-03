package com.example.rickandmortywithcompose.ui.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortywithcompose.navigation.graphs.HomeNavGraph
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.theme.RickAndMortyWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val bottomBarNavController: NavHostController = rememberNavController()


            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        with(mainViewModel) {
                            launch { navigationEvent(navController) }
                            launch {
                                navigateUpEvent(
                                    navController,
                                    bottomBarNavController
                                )
                            }
                        }
                    }
                }
            }
            RickAndMortyWithComposeTheme {
                HomeNavGraph(
                    navController = navController,
                    bottomBarNavController = bottomBarNavController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }

    private suspend fun navigationEvent(navController: NavHostController) {
        mainViewModel.navigationEvent.collectLatest { navigationData ->
            navController.navigate(navigationData.destination)
        }
    }

    private suspend fun navigateUpEvent(
        navController: NavHostController,
        bottomBarNavController: NavHostController,
    ) {
        mainViewModel.navigateUpEvent.collectLatest { navigateUpData ->
            navigateUpData?.let { data ->
                if (data.backStackNavGraph == HomeScreens.BottomBar::class) {
                    bottomBarNavController.navigateUp()
                }
            }
            navController.navigateUp()
        }
    }
}

