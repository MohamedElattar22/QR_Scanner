package com.mohamedelattar.qrscanner.screens.home.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.mohamedelattar.qrscanner.R
import com.mohamedelattar.qrscanner.screens.composables.EmptyIndicatorScreen
import com.mohamedelattar.qrscanner.screens.composables.QRListItem
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract

@Composable
fun QRHistoryScreen(
    modifier: Modifier = Modifier,
    state: HomeContract.QRScannerState,
    onAction: (HomeContract.QRScannerActions) -> Unit = {},
) {
    if (state.qrItems.isEmpty()) {
        EmptyIndicatorScreen(
            imageVector = ImageVector.vectorResource(R.drawable.history_ic),
            title = stringResource(R.string.scan_history),
            description = stringResource(R.string.find_scan_history)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            items(state.qrItems, key = { it.id }) {
                QRListItem(
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



}