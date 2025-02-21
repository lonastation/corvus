package com.linn.inventory.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.inventory.data.Item
import com.linn.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NestViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    private var _nestUiState = MutableStateFlow(NestUiState(listOf()))
    val nestUiState: StateFlow<NestUiState> = _nestUiState

    init {
        viewModelScope.launch {
            itemsRepository.getAllItemsStream().collect { items ->
                _nestUiState.value = NestUiState(items)
            }
        }
    }
}

data class NestUiState(val itemList: List<Item> = listOf())