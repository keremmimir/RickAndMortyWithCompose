package com.example.rickandmortywithcompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortywithcompose.ui.screens.home.homeEvent.HomeScreenEvent
import com.example.rickandmortywithcompose.ui.screens.home.homeState.HomeScreenState
import com.example.rickandmortywithcompose.data.repository.remote.ApiRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val apiRepositoryImpl: ApiRepositoryImpl) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val characterPager = uiState.map { it.searchQueryText }
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            apiRepositoryImpl.charactersPagingSource(query)
        }.cachedIn(viewModelScope)


    fun handleEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.SearchQueryChanged ->
                _uiState.update {
                    it.copy(searchQueryText = event.newQuery)
                }
        }
    }
}


