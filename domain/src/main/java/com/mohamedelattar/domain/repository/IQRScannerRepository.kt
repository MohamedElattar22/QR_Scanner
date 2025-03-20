package com.mohamedelattar.domain.repository

import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.domain.model.QRItem

interface IQRScannerRepository {
    suspend fun initializeQRScanner(): ScanOptions
    suspend fun insertQRItem(qrItem: QRItem)
    suspend fun addItemToFavourite(qrItem: QRItem)
    suspend fun removeItemFromFavourite(qrItem: QRItem)
    suspend fun getAllQRItems(): List<QRItem>
    suspend fun getAllFavouriteQRItems(): List<QRItem>
    suspend fun getQRItemById(id: Int): QRItem?
    suspend fun deleteQRItem(qrItem: QRItem)


}