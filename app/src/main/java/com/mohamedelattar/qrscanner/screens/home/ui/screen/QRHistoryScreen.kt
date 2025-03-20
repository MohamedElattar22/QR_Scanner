package com.mohamedelattar.qrscanner.screens.home.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamedelattar.qrscanner.screens.composables.QRHistoryListItem
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract

@Composable
fun QRHistoryScreen(
    modifier: Modifier = Modifier,
    state: HomeContract.QRScannerState,
    onAction: (HomeContract.QRScannerActions) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        items(state.qrItems, key = { it.id }) {
            QRHistoryListItem(
                qrItem = it,
                onAddFavorite = { isFavourite, qrItem ->
                    onAction(
                        HomeContract.QRScannerActions.ToggleQRItemFavourite(
                            qrItem = qrItem,
                            isFavourite = isFavourite
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }


}