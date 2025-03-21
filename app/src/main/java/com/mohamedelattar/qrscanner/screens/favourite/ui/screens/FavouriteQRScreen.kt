package com.mohamedelattar.qrscanner.screens.favourite.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mohamedelattar.qrscanner.screens.composables.QRListItem
import com.mohamedelattar.qrscanner.screens.composables.TopAppBarComp
import com.mohamedelattar.qrscanner.screens.favourite.viewmodel.FavouriteItemsContract
import com.mohamedelattar.qrscanner.screens.favourite.viewmodel.FavouriteItemsViewModel

@Composable
fun FavouriteQRScreen(
    navController: NavController,
    viewModel: FavouriteItemsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                hasNavigation = true,
                title = "Favourites",
                onNavigation = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {

        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                items(state.favouriteItems, key = { it.id }) {
                    QRListItem(
                        qrItem = it,
                        onAddFavorite = { _, qrItem ->
                            viewModel.onAction(
                                FavouriteItemsContract.FavouriteItemsActions.RemoveItemFromFavourite(
                                    qrItem
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }


    }
}