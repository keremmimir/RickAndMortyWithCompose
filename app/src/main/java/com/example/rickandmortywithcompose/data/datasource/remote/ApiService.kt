package com.example.rickandmortywithcompose.data.datasource.remote

import com.example.rickandmortywithcompose.data.model.ApiCharacterResponse
import com.example.rickandmortywithcompose.data.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?
        ): ApiCharacterResponse

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") ids: String) : Response<List<CharacterModel>>
}