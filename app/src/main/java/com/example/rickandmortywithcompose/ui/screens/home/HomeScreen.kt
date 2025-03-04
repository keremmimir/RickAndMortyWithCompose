package com.example.rickandmortywithcompose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortywithcompose.ui.component.CharacterList
import com.example.rickandmortywithcompose.ui.component.SearchTextField
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.screens.favorite.FavoriteScreenEvent
import com.example.rickandmortywithcompose.ui.theme.Background
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val lazyPagingItems = homeScreenViewModel.characterPager.collectAsLazyPagingItems()
    val uiState by homeScreenViewModel.uiState.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(true) {
        homeScreenViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is FavoriteScreenEvent.NavigateToDetail -> {
                    mainViewModel.navigateToDetailsScreen(event.character)
                }

                else -> Unit
            }
        }
    }

    LaunchedEffect(uiState.searchQueryText) {
        delay(100)
        listState.scrollToItem(0)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(
                query = uiState.searchQueryText,
                onSearchQueryChange = { query ->
                    homeScreenViewModel.handleEvent(
                        HomeScreenEvent.SearchQueryChanged(
                            query
                        )
                    )
                }
            )
            Spacer(Modifier.height(15.dp))
            CharacterList(
                listState = listState,
                lazyPagingItems = lazyPagingItems,
                favoriteList = null,
                onDetailNavigate = { character ->
                    homeScreenViewModel.handleEvent(HomeScreenEvent.OnClickedCharacter(character))
                }
            )
        }
    }
}




