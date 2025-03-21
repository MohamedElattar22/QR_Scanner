package com.mohamedelattar.qrscanner.screens.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.usecase.local.GetAllFavouriteQRItemsUC
import com.mohamedelattar.domain.usecase.local.RemoveQRItemFromFavouriteUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteItemsViewModel @Inject constructor(
    private val getAllFavouriteItemsUC: GetAllFavouriteQRItemsUC,
    private val removeItemFromFavouriteUC: RemoveQRItemFromFavouriteUC,
) : ViewModel() {

    private val _state = MutableStateFlow(FavouriteItemsContract.FavouriteItemsState())
    val state = _state.asStateFlow()

    init {
        getAllFavouriteItems()
    }

    fun onAction(action: FavouriteItemsContract.FavouriteItemsActions) {
        when (action) {
            is FavouriteItemsContract.FavouriteItemsActions.GetAllFavouriteItems -> {
                getAllFavouriteItems()
            }

            is FavouriteItemsContract.FavouriteItemsActions.RemoveItemFromFavourite -> {
                removeItemFromFavourite(action.qrItem)
            }

            is FavouriteItemsContract.FavouriteItemsActions.SelectQRItem -> {
                selectQRItem(action.qrItem)
            }

            is FavouriteItemsContract.FavouriteItemsActions.ShowDetailsSheet -> {
                showDetailsSheet(action.show)

            }
        }

    }

    private fun showDetailsSheet(showSheet: Boolean) {
        _state.update {
            it.copy(
                showDetailsSheet = showSheet
            )
        }
    }

    private fun selectQRItem(item: QRItem) {
        _state.update {
            it.copy(
                selectedQRItem = item
            )
        }
    }

    private fun removeItemFromFavourite(item: QRItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeItemFromFavouriteUC.invoke(qrItem = item)
            getAllFavouriteItems()
        }
    }

    private fun getAllFavouriteItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getAllFavouriteItemsUC.invoke()
            _state.update {
                it.copy(
                    favouriteItems = items
                )
            }
        }
    }


}