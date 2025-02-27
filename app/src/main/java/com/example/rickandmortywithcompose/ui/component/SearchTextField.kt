package com.example.rickandmortywithcompose.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortywithcompose.ui.theme.BottomBarColor

@Composable
fun SearchTextField(
    query: String,
    onSearchQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = {
            onSearchQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.White
            )
        },
        placeholder = { Text("Search character..", color = Color.White) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BottomBarColor,
            focusedContainerColor = BottomBarColor,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = 1
    )
}