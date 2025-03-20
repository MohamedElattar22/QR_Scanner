package com.mohamedelattar.qrscanner.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.usecase.local.InitializeQRScannerUC
import com.mohamedelattar.domain.usecase.local.InsertQRItemUC
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
) : ViewModel() {
    private val _scanOptions = MutableStateFlow(ScanOptions())
    val scanOptions = _scanOptions.asStateFlow()


    private val _state = MutableStateFlow(HomeContract.QRScannerState())
    val state = _state.asStateFlow()

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
        }

    }

    private fun initializeQRScanner() {
        viewModelScope.launch(Dispatchers.IO) {
            val options = initializeQRScanner.invoke()
            _scanOptions.value = options
        }
    }


}