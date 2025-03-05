package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

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