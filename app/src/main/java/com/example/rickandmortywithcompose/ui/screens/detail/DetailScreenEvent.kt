package com.example.rickandmortywithcompose.ui.screens.detail

sealed class DetailScreenEvent {
    data class OnClickFavoriteButton(val id: Int) : DetailScreenEvent()
    data object NavigateUp : DetailScreenEvent()
    data object OnClickBackButton : DetailScreenEvent()
}