package com.example.rickandmortywithcompose.navigation.graphs

import kotlin.reflect.KClass

data class NavigateUpData(
    val backStackNavGraph: KClass<*>,
)