package com.example.rickandmortywithcompose.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortywithcompose.data.model.ApiCharacterResponse
import com.example.rickandmortywithcompose.data.datasource.remote.ApiService
import com.example.rickandmortywithcompose.data.datasource.remote.pagingsource.CharacterPagingSource
import com.example.rickandmortywithcompose.data.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository {
    override suspend fun getCharacters(page: Int, name: String?): ApiCharacterResponse {
        return apiService.getCharacters(page, name)
    }

    override fun charactersPagingSource(name: String?): Flow<PagingData<CharacterModel>>  {
        return Pager(pagingSourceFactory = {CharacterPagingSource(apiService,name)},
            config = PagingConfig(pageSize = 20)
        ).flow
    }
}
