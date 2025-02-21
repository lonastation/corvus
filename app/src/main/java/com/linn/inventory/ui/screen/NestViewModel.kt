package com.linn.inventory.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.inventory.data.Item
import com.linn.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NestViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    val nestUiState: StateFlow<NestUiState> =
        itemsRepository.getAllItemsStream().map { NestUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NestUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class NestUiState(val itemList: List<Item> = listOf())