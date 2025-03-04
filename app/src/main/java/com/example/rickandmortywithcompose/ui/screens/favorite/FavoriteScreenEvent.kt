package com.example.rickandmortywithcompose.ui.screens.favorite

import com.example.rickandmortywithcompose.data.model.CharacterModel

sealed class FavoriteScreenEvent {
   data class NavigateToDetail(val character: CharacterModel) : FavoriteScreenEvent()
   data class OnClickedCharacter(val character: CharacterModel) : FavoriteScreenEvent()
}