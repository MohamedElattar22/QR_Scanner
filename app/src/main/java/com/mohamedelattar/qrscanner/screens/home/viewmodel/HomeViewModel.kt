package com.mohamedelattar.qrscanner.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.usecase.local.AddQRItemToFavouriteUC
import com.mohamedelattar.domain.usecase.local.GetAllQRItemsUC
import com.mohamedelattar.domain.usecase.local.InitializeQRScannerUC
import com.mohamedelattar.domain.usecase.local.InsertQRItemUC
import com.mohamedelattar.domain.usecase.local.RemoveQRItemFromFavouriteUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val initializeQRScanner: InitializeQRScannerUC,
    private val insertQRItem: InsertQRItemUC,
    private val getQRItems: GetAllQRItemsUC,
    private val addQRItemToFavourite: AddQRItemToFavouriteUC,
    private val removeQRItemFromFavourite: RemoveQRItemFromFavouriteUC,
) : ViewModel() {
    private val _scanOptions = MutableStateFlow(ScanOptions())
    val scanOptions = _scanOptions.asStateFlow()


    private val _state = MutableStateFlow(HomeContract.QRScannerState())
    val state = _state.asStateFlow()

    init {
        getAllQRItems()

    }

    fun onAction(action: HomeContract.QRScannerActions) {
        when (action) {
            is HomeContract.QRScannerActions.InitializeQRScanner -> {
                initializeQRScanner()
            }

            is HomeContract.QRScannerActions.InsertQRItem -> {
                insertQRItem(action.qrItem)

            }

            is HomeContract.QRScannerActions.ShowItemDataSheet -> {
                _state.update {
                    it.copy(
                        showItemDataSheet = action.show
                    )
                }
            }

            is HomeContract.QRScannerActions.SetQRContent -> {
                setQRContentText(action.content)
            }

            HomeContract.QRScannerActions.GetAllQRItems -> {
                getAllQRItems()
            }

            is HomeContract.QRScannerActions.ToggleQRItemFavourite -> {
                if (action.isFavourite) {
                    addQRItemToFavourite(action.qrItem)
                } else {
                    removeQRItemFromFavourite(action.qrItem)
                }

            }
        }
    }

    private fun getAllQRItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getQRItems.invoke()
            _state.update {
                it.copy(
                    qrItems = items
                )
            }
        }
    }

    private fun setQRContentText(content: String) {
        _state.update {
            it.copy(
                qrContent = content
            )
        }

    }

    private fun insertQRItem(item: QRItem) {
        viewModelScope.launch(Dispatchers.IO) {
            insertQRItem.invoke(
                item
            )
            setQRContentText(item.content)
            getAllQRItems()
        }

    }

    private fun initializeQRScanner() {
        viewModelScope.launch(Dispatchers.IO) {
            val options = initializeQRScanner.invoke()
            _scanOptions.value = options
        }
    }

    private fun addQRItemToFavourite(qrItem: QRItem) {
        viewModelScope.launch(Dispatchers.IO) {
            addQRItemToFavourite.invoke(qrItem)
            getAllQRItems()
        }
    }

    private fun removeQRItemFromFavourite(qrItem: QRItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeQRItemFromFavourite.invoke(qrItem)
            getAllQRItems()
        }
    }


}