package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortywithcompose.data.model.CharacterModel
import com.example.rickandmortywithcompose.ui.theme.CardBackgroud

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
            DetailCharacterName(characterName = detailItem.name)
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