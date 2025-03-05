package com.example.rickandmortywithcompose.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.component.DetailCard
import com.example.rickandmortywithcompose.ui.component.DetailCharacterImage
import com.example.rickandmortywithcompose.ui.component.DetailTopBar
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.theme.Background
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    mainViewModel: MainViewModel,
    arguments: HomeScreens.Detail,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by detailViewModel.uiState.collectAsState()
    val character = arguments.character

    LaunchedEffect(character.id) {
        detailViewModel.isFavorite(character.id)
    }

    LaunchedEffect(true) {
        detailViewModel.uiEvent.collectLatest { event ->
            when (event) {
                DetailScreenEvent.NavigateUp -> {
                    mainViewModel.navigateUp()
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailTopBar(
                backOnClick = {
                    detailViewModel.onEvent(DetailScreenEvent.OnClickBackButton)
                },
                favoriteOnClick = {
                    detailViewModel.onEvent(DetailScreenEvent.OnClickFavoriteButton(character.id))
                },
                isFavorite = uiState.isFavorite
            )
            DetailCharacterImage(character.image)
            DetailCard(character)
        }
    }
}

