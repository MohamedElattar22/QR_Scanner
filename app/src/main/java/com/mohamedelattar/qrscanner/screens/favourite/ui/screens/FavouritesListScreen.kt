package com.mohamedelattar.qrscanner.screens.favourite.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.screens.composables.QRListItem

@Composable
fun FavouriteListScreen(
    modifier: Modifier = Modifier,
    favouriteItems: List<QRItem>,
    onAddFavorite: (QRItem) -> Unit,
    onItemClick: (QRItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(10.dp)
    ) {
        items(favouriteItems, key = { it.id }) {
            QRListItem(
                qrItem = it,
                onAddFavorite = { _, qrItem ->
                    onAddFavorite(qrItem)
                },
                onItemClick = {
                    onItemClick(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}