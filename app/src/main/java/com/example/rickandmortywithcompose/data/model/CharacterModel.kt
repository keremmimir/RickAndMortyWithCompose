package com.example.rickandmortywithcompose.data.model

import kotlinx.serialization.Serializable


@Serializable
data class CharacterModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val url: String
)