package com.linn.inventory.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val label:String , val icon: ImageVector) {
    data object Home: Screens("Home", "What's new", Icons.Default.CatchingPokemon)
    data object Nest: Screens("Nest", "Nest", Icons.Outlined.TravelExplore)
}