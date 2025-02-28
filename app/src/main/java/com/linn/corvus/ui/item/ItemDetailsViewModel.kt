/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linn.corvus.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.corvus.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private var _itemUiState = MutableStateFlow(ItemUiState(itemDetails = ItemDetails()))
    val itemUiState: StateFlow<ItemUiState> = _itemUiState

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.ITEM_ID_ARG])

    init {
        viewModelScope.launch {
            itemsRepository.getItemStream(itemId).collect { item ->
                item?.let {
                    _itemUiState.value = ItemUiState(itemDetails = it.toItemDetails())
                }
            }
        }
    }

    suspend fun deleteItem() {
        itemsRepository.deleteItem(item = _itemUiState.value.itemDetails.toItem())
    }
}
