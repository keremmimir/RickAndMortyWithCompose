package com.example.rickandmortywithcompose.navigation

import com.example.rickandmortywithcompose.data.model.CharacterModel
import kotlinx.serialization.Serializable

sealed class HomeScreens {
    @Serializable
    object BottomBar {
        @Serializable
        object Home

        @Serializable
        object Favorite
    }

    @Serializable
    class Detail(val character: CharacterModel)
}