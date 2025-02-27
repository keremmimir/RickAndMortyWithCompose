package com.example.rickandmortywithcompose.ui.screens.home.homeEvent

sealed class HomeScreenEvent {
    data class SearchQueryChanged(val newQuery: String) : HomeScreenEvent()
}