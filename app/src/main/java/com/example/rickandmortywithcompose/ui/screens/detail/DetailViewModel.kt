package com.example.rickandmortywithcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywithcompose.data.repository.local.RoomRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val roomRepositoryImpl: RoomRepositoryImpl) :
    ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState

    private val _uiEvent = MutableSharedFlow<DetailScreenEvent>()
    val uiEvent: SharedFlow<DetailScreenEvent> = _uiEvent

    fun onEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.OnClickFavoriteButton -> {
                toggleFavorite(event.id)
            }

            DetailScreenEvent.OnClickBackButton -> {
                viewModelScope.launch {
                    _uiEvent.emit(DetailScreenEvent.NavigateUp)
                }
            }

            else -> Unit
        }
    }

    private fun toggleFavorite(characterId: Int) {
        viewModelScope.launch {
            val isFav = _uiState.value.isFavorite
            if (isFav) {
                roomRepositoryImpl.deleteFavoriteCharacter(characterId)
            } else {
                roomRepositoryImpl.addFavoriteCharacter(characterId)
            }
            _uiState.value = _uiState.value.copy(isFavorite = !isFav)
        }
    }

    fun isFavorite(characterId: Int) {
        viewModelScope.launch {
            val isFavorite = roomRepositoryImpl.isFavorite(characterId)
            _uiState.value = _uiState.value.copy(isFavorite = isFavorite)
        }
    }
}