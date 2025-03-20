package com.mohamedelattar.data.repository

import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.data.repository.local.contract.IQRScannerLocalDS
import com.mohamedelattar.data.repository.local.contract.IQRScannerOptionsDS
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.repository.IQRScannerRepository
import javax.inject.Inject

class QRScannerRepository @Inject constructor(
    private val qrScannerLocalDS: IQRScannerLocalDS,
    private val qrScannerOptionsDS: IQRScannerOptionsDS,
) : IQRScannerRepository {
    override suspend fun initializeQRScanner(): ScanOptions {
        return qrScannerOptionsDS.initializeQRScanner()
    }

    override suspend fun insertQRItem(qrItem: QRItem) {
        qrScannerLocalDS.insertQRItem(qrItem)
    }

    override suspend fun addItemToFavourite(qrItem: QRItem) {
        return qrScannerLocalDS.addItemToFavourite(qrItem)
    }

    override suspend fun removeItemFromFavourite(qrItem: QRItem) {
        return qrScannerLocalDS.removeItemFromFavourite(qrItem)
    }

    override suspend fun getAllQRItems(): List<QRItem> {
        return qrScannerLocalDS.getAllQRItems()
    }

    override suspend fun getAllFavouriteQRItems(): List<QRItem> {
        return qrScannerLocalDS.getAllFavouriteQRItems()
    }

    override suspend fun getQRItemById(id: Int): QRItem? {
        return qrScannerLocalDS.getQRItemById(id)
    }

    override suspend fun deleteQRItem(qrItem: QRItem) {
        return qrScannerLocalDS.deleteQRItem(qrItem)
    }

}