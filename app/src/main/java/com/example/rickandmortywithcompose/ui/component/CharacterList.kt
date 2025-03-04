package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortywithcompose.data.model.CharacterModel

@Composable
fun CharacterList(
    listState: LazyGridState,
    lazyPagingItems: LazyPagingItems<CharacterModel>? = null,
    favoriteList: List<CharacterModel>? = null,
    onDetailNavigate: (CharacterModel) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (lazyPagingItems != null) {
                items(lazyPagingItems.itemCount) { index ->
                    val character = lazyPagingItems[index]
                    character?.let {
                        CharacterListItem(
                            characterItem = it,
                            detailNavigate = { onDetailNavigate(it) })
                    }
                }
                when {
                    lazyPagingItems.loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingIndicator()
                        }
                    }

                    lazyPagingItems.loadState.refresh is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingIndicator()
                        }
                    }
                }
            } else if (favoriteList?.isNotEmpty() == true) {
                items(favoriteList.size) { index ->
                    val character = favoriteList[index]
                    CharacterListItem(
                        characterItem = character,
                        detailNavigate = { onDetailNavigate(character) })
                }
            }
        }
        if (favoriteList?.isEmpty() == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Empty List")
            }
        }
    }
}