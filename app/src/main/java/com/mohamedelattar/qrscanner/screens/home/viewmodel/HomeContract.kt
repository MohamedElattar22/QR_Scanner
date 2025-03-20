package com.mohamedelattar.qrscanner.screens.home.viewmodel

import com.mohamedelattar.domain.model.QRItem

interface HomeContract {
    sealed class QRScannerActions() {
        data object InitializeQRScanner : QRScannerActions()
        data class InsertQRItem(val qrItem: QRItem) : QRScannerActions()
        data class ShowItemDataSheet(val show: Boolean) : QRScannerActions()
        data class SetQRContent(val content: String) : QRScannerActions()

    }

    data class QRScannerState(
        val qrItems: List<QRItem> = emptyList(),
        val qrItem: QRItem? = null,
        val qrContent: String = "",
        val showItemDataSheet: Boolean = false,
    )
}