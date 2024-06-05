package com.example.inventory.ui.navigation

sealed class Screens(val route: String ) {
    data object Clothes: Screens("clothes")
    data object Tools: Screens("tools")
    data object Supplies: Screens("Supplies")
}