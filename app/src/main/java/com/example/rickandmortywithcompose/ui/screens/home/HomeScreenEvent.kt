package com.example.rickandmortywithcompose.ui.screens.home

import com.example.rickandmortywithcompose.data.model.CharacterModel

sealed class HomeScreenEvent {
    data class SearchQueryChanged(val newQuery: String) : HomeScreenEvent()
    data class OnClickedCharacter(val character: CharacterModel) : HomeScreenEvent()
    data class NavigateToDetail(val character: CharacterModel) : HomeScreenEvent()
}