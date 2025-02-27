package com.example.rickandmortywithcompose.ui.screens.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortywithcompose.data.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<String>()

    val navigationEvent: SharedFlow<String> = _navigationEvent

    init {
        Log.e("MainViewModel", "init MainViewModel")
    }

    fun navigateToDetailsScreen(character: CharacterModel) {
        viewModelScope.launch { _navigationEvent.emit("detail") }
    }
}