package com.example.rickandmortywithcompose.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortywithcompose.ui.component.CharacterList
import com.example.rickandmortywithcompose.ui.component.LoadingIndicator
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.theme.Background
import com.example.rickandmortywithcompose.ui.theme.TextColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel,
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
) {
    val listState = rememberLazyGridState()
    val favoriteUiState by favoriteScreenViewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        favoriteScreenViewModel.getAllFavoriteCharacters()
    }

    LaunchedEffect(true) {
        favoriteScreenViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is FavoriteScreenEvent.NavigateToDetail -> {
                    mainViewModel.navigateToDetailsScreen(event.character)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when {
                favoriteUiState.isLoading -> {
                    LoadingIndicator()
                }

                favoriteUiState.errorMessage != null -> {
                    Text(
                        text = favoriteUiState.errorMessage ?: "",
                        color = Color.Red,
                    )
                }

                else -> {
                    Text(
                        "Favorite List",
                        fontSize = 25.sp,
                        color = TextColor,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    CharacterList(
                        listState = listState,
                        lazyPagingItems = null,
                        favoriteList = favoriteUiState.favoriteCharacterList,
                        onDetailNavigate = { character ->
                            favoriteScreenViewModel.onEvent(
                                FavoriteScreenEvent.OnClickedCharacter(
                                    character
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}