package com.example.rickandmortywithcompose.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywithcompose.data.model.Result
import com.example.rickandmortywithcompose.data.repository.local.RoomRepositoryImpl
import com.example.rickandmortywithcompose.data.repository.remote.ApiRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val roomRepositoryImpl: RoomRepositoryImpl,
    private val apiRepositoryImpl: ApiRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteScreenState())
    val uiState: StateFlow<FavoriteScreenState> = _uiState

    private val _uiEvent = MutableSharedFlow<FavoriteScreenEvent>()
    val uiEvent: SharedFlow<FavoriteScreenEvent> = _uiEvent

    fun onEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.OnClickedCharacter -> {
                viewModelScope.launch {
                    _uiEvent.emit(FavoriteScreenEvent.NavigateToDetail(event.character))
                }
            }

            else -> Unit
        }
    }

    fun getAllFavoriteCharacters() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {

            val favoriteCharacterIds = roomRepositoryImpl.getFavoriteCharacterIds()
            if (favoriteCharacterIds.isNotEmpty()) {
                when (val response =
                    apiRepositoryImpl.getMultipleCharacters(favoriteCharacterIds)) {
                    is Result.Success -> _uiState.value =
                        _uiState.value.copy(
                            favoriteCharacterList = response.data,
                            isLoading = false
                        )

                    is Result.Error -> _uiState.value =
                        _uiState.value.copy(
                            errorMessage = response.message,
                            isLoading = false
                        )

                    Result.Loading -> _uiState.value =
                        _uiState.value.copy(isLoading = true)
                }
            } else {
                _uiState.value =
                    _uiState.value.copy(
                        favoriteCharacterList = emptyList(),
                        isLoading = false
                    )
            }

        }
    }
}