package com.linn.corvus.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.linn.corvus.R
import com.linn.corvus.data.Item
import com.linn.corvus.ui.AppViewModelProvider
import com.linn.corvus.ui.theme.CorvusTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NestScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NestViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val nestUiState by viewModel.nestUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_entry_title)
                )
            }
        },
    ) {
        NestBody(
            itemList = nestUiState.itemList,
            onItemClick = navigateToItemUpdate,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun NestBody(
    itemList: List<Item>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    var isListView by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { isListView = !isListView },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    imageVector = if (isListView) Icons.Outlined.Widgets else Icons.Outlined.Category,
                    contentDescription = if (isListView)
                        stringResource(R.string.grouped_view)
                    else stringResource(R.string.list_view),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            if (isListView) {
                NestList(
                    itemList = itemList,
                    onItemClick = { onItemClick(it.id) },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                )
            } else {
                GroupedNestList(
                    itemList = itemList,
                    onItemClick = { onItemClick(it.id) },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
private fun NestList(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(
            count = itemList.size,
            key = { itemList[it].id }
        ) { index ->
            NestItem(
                item = itemList[index],
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(itemList[index]) }
            )
        }
    }
}

@Composable
private fun GroupedNestList(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    val groupedItems = itemList.groupBy { it.type }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        groupedItems.forEach { (type, items) ->
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = type,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }

            items(
                count = items.size,
                key = { items[it].id }
            ) { index ->
                NestItem(
                    item = items[index],
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clickable { onItemClick(items[index]) }
                )
            }
        }
    }
}

@Composable
private fun NestItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            if (item.photo.isBlank()) {
                Image(
                    painter = painterResource(id = R.drawable.picture1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(item.photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = item.type,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Color.Black.copy(alpha = 0.6f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                Text(
                    text = "#${item.id} ${item.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = item.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = stringResource(R.string.in_stock, item.quantity),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NestBodyPreview() {
    CorvusTheme {
        NestBody(listOf(
            Item(1, "A", "", 20, ""),
            Item(2, "B", "", 30, ""),
            Item(3, "T", "", 50, "")
        ), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NestBodyEmptyListPreview() {
    CorvusTheme {
        NestBody(listOf(), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NestItemPreview() {
    CorvusTheme {
        NestItem(
            Item(1, "外套", "Game", 20, "")
        )
    }
}