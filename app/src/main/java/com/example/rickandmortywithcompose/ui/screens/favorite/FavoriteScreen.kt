package com.example.rickandmortywithcompose.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.theme.Background

@Composable
fun FavoriteScreen(paddingValues: PaddingValues,mainViewModel: MainViewModel){
    Box(modifier = Modifier.fillMaxSize().background(Background)){
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Text("Favorite Screen")
        }
    }
}