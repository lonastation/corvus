package com.linn.inventory.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val label:String , val icon: ImageVector) {
    data object Clothes: Screens("clothes", "clothes", Icons.Filled.ShoppingCart)
    data object Tools: Screens("tools", "tools", Icons.Filled.Build)
    data object Supplies: Screens("supplies", "supplies", Icons.Filled.DateRange)
}