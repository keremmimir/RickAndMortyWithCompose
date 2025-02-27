package com.example.rickandmortywithcompose.data.datasource.remote

import com.example.rickandmortywithcompose.data.model.ApiCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?
        ): ApiCharacterResponse
}