package com.example.rickandmortywithcompose.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortywithcompose.data.model.CharacterModel
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.screens.activity.MainViewModel
import com.example.rickandmortywithcompose.ui.theme.Background
import com.example.rickandmortywithcompose.ui.theme.CardBackgroud
import com.example.rickandmortywithcompose.ui.theme.TextColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    mainViewModel: MainViewModel,
    arguments: HomeScreens.Detail,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by detailViewModel.uiState.collectAsState()
    val character = arguments.character

    LaunchedEffect(character.id) {
        detailViewModel.isFavorite(character.id)
    }

    LaunchedEffect(true) {
        detailViewModel.uiEvent.collectLatest { event ->
            when (event) {
                DetailScreenEvent.NavigateUp -> {
                    mainViewModel.navigateUp()
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                backOnClick = {
                    detailViewModel.onEvent(DetailScreenEvent.OnClickBackButton)
                },
                favoriteOnClick = {
                    detailViewModel.onEvent(DetailScreenEvent.OnClickFavoriteButton(character.id))
                },
                isFavorite = uiState.isFavorite
            )
            DetailCharacterImage(character.image)
            DetailCard(character)
        }
    }
}

@Composable
fun TopBar(backOnClick: () -> Unit, favoriteOnClick: () -> Unit, isFavorite: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = {
            backOnClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                "",
                tint = Color.White
            )
        }

        IconButton(onClick = { favoriteOnClick() }) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                "",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailCharacterImage(detailImage: String) {
    GlideImage(
        model = detailImage,
        contentDescription = "",
        modifier = Modifier
            .size(250.dp, 250.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp))
    )
}

@Composable
fun DetailCard(detailItem: CharacterModel) {

    val characterDetails = listOf(
        "Status" to (detailItem.status),
        "Species" to (detailItem.species),
        "Gender" to (detailItem.gender),
        "Origin" to (detailItem.origin.name),
        "Location" to (detailItem.location.name),
        "Episode" to (detailItem.episode.size.toString())
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp))
            .background(CardBackgroud)
    ) {
        Column(Modifier.fillMaxSize()) {
            DetailCharacterTitle(characterTitle = detailItem.name)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color.Black
            )
            characterDetails.forEach { (title, response) ->
                DetailCharacterItem(
                    itemTitle = title,
                    itemResponse = response,
                )
            }
        }
    }
}

@Composable
fun DetailCharacterTitle(characterTitle: String) {
    Text(
        characterTitle,
        textAlign = TextAlign.Center,
        color = TextColor,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun DetailCharacterItem(
    itemTitle: String,
    itemResponse: String,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "$itemTitle :", fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )
        Text(
            text = itemResponse, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
        )
    }
}