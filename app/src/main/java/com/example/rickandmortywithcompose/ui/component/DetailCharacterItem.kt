package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortywithcompose.ui.theme.TextColor

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