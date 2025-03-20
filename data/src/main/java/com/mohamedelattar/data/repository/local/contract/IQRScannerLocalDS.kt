package com.mohamedelattar.data.repository.local.contract

import com.mohamedelattar.domain.model.QRItem

interface IQRScannerLocalDS {
    suspend fun insertQRItem(qrItem: QRItem)
    suspend fun addItemToFavourite(qrItem: QRItem)
    suspend fun removeItemFromFavourite(qrItem: QRItem)
    suspend fun getAllQRItems(): List<QRItem>
    suspend fun getAllFavouriteQRItems(): List<QRItem>
    suspend fun getQRItemById(id: Int): QRItem?
    suspend fun deleteQRItem(qrItem: QRItem)


}