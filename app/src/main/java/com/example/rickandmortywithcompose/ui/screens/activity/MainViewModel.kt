package com.example.rickandmortywithcompose.ui.screens.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywithcompose.data.model.CharacterModel
import com.example.rickandmortywithcompose.navigation.graphs.NavigateUpData
import com.example.rickandmortywithcompose.navigation.graphs.NavigationData
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel  : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<NavigationData>()
    val navigationEvent: SharedFlow<NavigationData> = _navigationEvent

    private val _navigateUpEvent = MutableSharedFlow<NavigateUpData?>()
    val navigateUpEvent: SharedFlow<NavigateUpData?> = _navigateUpEvent

    fun navigateToDetailsScreen(character: CharacterModel) {
        viewModelScope.launch {
            _navigationEvent.emit(
                NavigationData(
                    destination = HomeScreens.Detail(
                        character = character
                    )
                )
            )
        }
    }
    fun navigateUp(navigateUpData: NavigateUpData? = null) {
        viewModelScope.launch {
            _navigateUpEvent.emit(navigateUpData)
        }
    }
}