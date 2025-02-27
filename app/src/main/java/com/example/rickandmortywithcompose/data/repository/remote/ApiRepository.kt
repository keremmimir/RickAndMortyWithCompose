package com.example.rickandmortywithcompose.data.repository.remote

import androidx.paging.PagingData
import com.example.rickandmortywithcompose.data.model.ApiCharacterResponse
import com.example.rickandmortywithcompose.data.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getCharacters(page: Int, name: String?): ApiCharacterResponse
    fun charactersPagingSource(name: String?): Flow<PagingData<CharacterModel>>
}