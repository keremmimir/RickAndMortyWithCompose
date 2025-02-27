package com.example.rickandmortywithcompose

import androidx.lifecycle.ViewModel
import com.example.rickandmortywithcompose.data.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel() {
    private val _detailItem = MutableStateFlow<CharacterModel?>(null)
    val detailItem:StateFlow<CharacterModel?> = _detailItem.asStateFlow()

    fun detailItem(characterModel: CharacterModel){
        _detailItem.value = characterModel
    }
}