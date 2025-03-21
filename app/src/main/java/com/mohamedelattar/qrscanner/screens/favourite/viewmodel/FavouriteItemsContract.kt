package com.mohamedelattar.qrscanner.screens.favourite.viewmodel

import com.mohamedelattar.domain.model.QRItem

interface FavouriteItemsContract {
    sealed class FavouriteItemsActions {
        data object GetAllFavouriteItems : FavouriteItemsActions()
        data class RemoveItemFromFavourite(val qrItem: QRItem) : FavouriteItemsActions()
        data class ShowDetailsSheet(val show: Boolean) : FavouriteItemsActions()
        data class SelectQRItem(val qrItem: QRItem) : FavouriteItemsActions()


    }


    data class FavouriteItemsState(
        val favouriteItems: List<QRItem> = emptyList(),
        val showDetailsSheet: Boolean = false,
        val selectedQRItem: QRItem = QRItem(
            id = 0,
            title = "",
            content = "",
            isFavorite = false,
            createdAt = 0
        ),

    )
}