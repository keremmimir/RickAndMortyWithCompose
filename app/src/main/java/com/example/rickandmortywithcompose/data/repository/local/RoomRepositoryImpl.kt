package com.example.rickandmortywithcompose.data.repository.local

import com.example.rickandmortywithcompose.data.datasource.local.dao.CharacterDao
import com.example.rickandmortywithcompose.data.model.FavoriteCharacter
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val characterDao: CharacterDao) : RoomRepository {
    override suspend fun getFavoriteCharacterIds(): List<Int> {
        return characterDao.getAllFavoriteCharacters().map { it.characterId }
    }

    override suspend fun addFavoriteCharacter(id: Int) {
        characterDao.insert(FavoriteCharacter(id))
    }

    override suspend fun deleteFavoriteCharacter(id: Int) {
        characterDao.delete(id)
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return characterDao.isFavorite(id)
    }
}