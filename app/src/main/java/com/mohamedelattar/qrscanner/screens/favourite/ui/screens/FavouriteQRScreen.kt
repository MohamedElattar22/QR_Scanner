package com.mohamedelattar.qrscanner.screens.favourite.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mohamedelattar.qrscanner.R
import com.mohamedelattar.qrscanner.screens.composables.EmptyIndicatorScreen
import com.mohamedelattar.qrscanner.screens.composables.QRItemDetailsSheet
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
            if (state.favouriteItems.isEmpty()) {
                EmptyIndicatorScreen(
                    imageVector = ImageVector.vectorResource(R.drawable.folder_heart),
                    title = stringResource(R.string.favourite_scans),
                    description = stringResource(R.string.here_you_will_find)
                )
            } else {
                FavouriteListScreen(
                    favouriteItems = state.favouriteItems,
                    onAddFavorite = {
                        viewModel.onAction(
                            FavouriteItemsContract.FavouriteItemsActions.RemoveItemFromFavourite(
                                it
                            )
                        )
                    },
                    onItemClick = {
                        viewModel.onAction(
                            FavouriteItemsContract.FavouriteItemsActions.ShowDetailsSheet(
                                true
                            )
                        )

                        viewModel.onAction(
                            FavouriteItemsContract.FavouriteItemsActions.SelectQRItem(
                                it
                            )
                        )
                    }

                )
            }

            if (state.showDetailsSheet) {
                QRItemDetailsSheet(
                    qrItem = state.selectedQRItem,
                    onDismiss = {
                        viewModel.onAction(
                            FavouriteItemsContract.FavouriteItemsActions.ShowDetailsSheet(
                                false
                            )
                        )
                    }
                )
            }

        }


    }
}