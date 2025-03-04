package com.example.rickandmortywithcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacter(@PrimaryKey val characterId : Int)
