package com.example.rickandmortywithcompose.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortywithcompose.data.repository.local.FavoriteCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCharacter: FavoriteCharacter)

    @Query("DELETE FROM favorite_characters WHERE characterId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM favorite_characters")
    suspend fun getAllFavoriteCharacters(): List<FavoriteCharacter>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_characters WHERE characterId = :id)")
    suspend fun isFavorite(id: Int): Boolean
}