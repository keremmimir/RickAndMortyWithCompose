package com.example.rickandmortywithcompose.navigation

import kotlin.reflect.KClass

data class NavigateUpData(
    val backStackNavGraph: KClass<*>,
)