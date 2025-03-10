package com.example.rickandmortywithcompose.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortywithcompose.data.datasource.local.dao.CharacterDao
import com.example.rickandmortywithcompose.data.repository.local.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}