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

package com.linn.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.linn.inventory.ui.item.ItemDetailsDestination
import com.linn.inventory.ui.item.ItemDetailsScreen
import com.linn.inventory.ui.item.ItemEditDestination
import com.linn.inventory.ui.item.ItemEditScreen
import com.linn.inventory.ui.item.ItemEntryDestination
import com.linn.inventory.ui.item.ItemEntryScreen
import com.linn.inventory.ui.screen.HomeScreen
import com.linn.inventory.ui.screen.NestScreen

/**
 * Provides Navigation graph for the application.
 */
const val PHOTO_URI_ARG = "photoUri"
val itemEntryRoute = "${ItemEntryDestination.route}?$PHOTO_URI_ARG={$PHOTO_URI_ARG}"

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeScreen(
                navigateToItemEntry = { photoUri ->
                    navController.navigate("${ItemEntryDestination.route}?$PHOTO_URI_ARG=$photoUri")
                }
            )
        }
        composable(Screens.Nest.route) {
            NestScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }

        composable(
            route = itemEntryRoute,
            arguments = listOf(
                navArgument(PHOTO_URI_ARG) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                initialPhotoUri = it.arguments?.getString(PHOTO_URI_ARG)
            )
        }

        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
