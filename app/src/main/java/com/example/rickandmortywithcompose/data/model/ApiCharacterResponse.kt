package com.example.rickandmortywithcompose.data.model

data class ApiCharacterResponse(
    val info: Info,
    val results: List<CharacterModel>
)