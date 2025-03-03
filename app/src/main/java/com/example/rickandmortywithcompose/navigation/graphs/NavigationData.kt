package com.example.rickandmortywithcompose.navigation.graphs

data class NavigationData(
    val destination: Any,
    val popupTo: Any? = null,
    val popupToInclusive: Boolean = false,
)