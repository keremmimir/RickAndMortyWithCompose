package com.example.rickandmortywithcompose.ui.screens.favorite

import com.example.rickandmortywithcompose.data.model.CharacterModel

data class FavoriteScreenState(
    val favoriteCharacterList:List<CharacterModel>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)