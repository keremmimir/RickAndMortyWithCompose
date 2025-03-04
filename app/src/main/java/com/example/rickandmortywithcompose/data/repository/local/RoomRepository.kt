package com.example.rickandmortywithcompose.data.repository.local

interface RoomRepository {
    suspend fun getFavoriteCharacterIds(): List<Int>
    suspend fun addFavoriteCharacter(id : Int)
    suspend fun deleteFavoriteCharacter(id : Int)
    suspend fun isFavorite(id: Int) : Boolean
}