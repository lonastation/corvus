package com.linn.corvus.ui.item

import com.linn.corvus.data.Item

data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val quantity: String = "",
    val photo: String = ""
)

fun Item.toItemDetails() = ItemDetails(
    id = id,
    name = name,
    type = type,
    quantity = quantity.toString(),
    photo = photo
)

fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    type = type,
    quantity = quantity.toIntOrNull() ?: 1,
    photo = photo
)