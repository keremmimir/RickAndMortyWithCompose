package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortywithcompose.data.model.CharacterModel
import com.example.rickandmortywithcompose.ui.theme.CardBackgroud
import com.example.rickandmortywithcompose.ui.theme.TextColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterListItem(characterItem: CharacterModel, detailNavigate: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(CardBackgroud)
            .border(
                2.dp, Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {detailNavigate()}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = characterItem.image,
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(180.dp, 180.dp)
                    .clip(RoundedCornerShape(15.dp)),
            )

            Spacer(Modifier.height(4.dp))
            Text(
                characterItem.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = TextColor,
                minLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp)
            )
        }
    }
}