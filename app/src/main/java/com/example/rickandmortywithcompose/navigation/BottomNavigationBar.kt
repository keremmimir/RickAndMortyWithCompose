package com.example.rickandmortywithcompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortywithcompose.R
import com.example.rickandmortywithcompose.navigation.screen.HomeScreens
import com.example.rickandmortywithcompose.ui.theme.BottomBarColor

@Composable
fun BottomNavigationBar(
    currentDestinationRoute: String?,
    onHomeBottomBarItemSelected: () -> Unit,
    onFavoriteActionButtonSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BottomBarColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(insets = WindowInsets.navigationBars)
                .padding(10.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavigationItem(
                icon = R.drawable.baseline_home_24,
                isSelected = currentDestinationRoute == HomeScreens.BottomBar.Home::class.qualifiedName!!,
                onPressed = {
                    if ((currentDestinationRoute == HomeScreens.BottomBar.Home::class.qualifiedName!!).not())
                        onHomeBottomBarItemSelected()
                }
            )
            BottomNavigationItem(
                icon = R.drawable.baseline_favorite_24,
                isSelected = currentDestinationRoute == HomeScreens.BottomBar.Favorite::class.qualifiedName!!,
                onPressed = {
                    if ((currentDestinationRoute == HomeScreens.BottomBar.Favorite::class.qualifiedName!!).not())
                        onFavoriteActionButtonSelected()
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(icon: Int, isSelected: Boolean, onPressed: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onPressed.invoke() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = if (isSelected) Color.White else Color.Black
        )

        Spacer(Modifier.height(5.dp))
    }
}