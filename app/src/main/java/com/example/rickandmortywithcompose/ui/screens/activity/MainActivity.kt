package com.example.rickandmortywithcompose.ui.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortywithcompose.navigation.HomeNavGraph
import com.example.rickandmortywithcompose.ui.theme.RickAndMortyWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val bottomBarNavController: NavHostController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()

//            LaunchedEffect(Unit) {
//                lifecycleScope.launch {
//                    mainViewModel.navigationEvent.collectLatest { route ->
//                        navController.navigate(route)
//                    }
//                }
//            }
            RickAndMortyWithComposeTheme {
                HomeNavGraph(
                    navController = navController,
                    bottomBarNavController = bottomBarNavController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }

}
