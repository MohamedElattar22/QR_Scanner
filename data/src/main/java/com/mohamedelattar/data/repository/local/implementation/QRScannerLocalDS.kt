package com.mohamedelattar.data.repository.local.implementation

import com.mohamedelattar.data.common.local.room.dao.QRItemDao
import com.mohamedelattar.data.mapper.QRItemMapper
import com.mohamedelattar.data.repository.local.contract.IQRScannerLocalDS
import com.mohamedelattar.domain.model.QRItem
import javax.inject.Inject

class QRScannerLocalDS @Inject constructor(
    private val qrItemDao: QRItemDao,
) : IQRScannerLocalDS {

    override suspend fun insertQRItem(qrItem: QRItem) {
        val qrItemEntity = QRItemMapper.domainToEntity(qrItem)
        qrItemDao.insertQRItem(qrItemEntity)
    }

    override suspend fun addItemToFavourite(qrItem: QRItem) {
        val qrItemEntity = QRItemMapper.domainToEntity(qrItem)
        return qrItemDao.addItemToFavourite(qrItemEntity.id)
    }

    override suspend fun removeItemFromFavourite(qrItem: QRItem) {
        val qrItemEntity = QRItemMapper.domainToEntity(qrItem)
        qrItemDao.removeItemFromFavourite(qrItemEntity.id)
    }

    override suspend fun getAllQRItems(): List<QRItem> {
        val itemsList = qrItemDao.getAllQRItems()
        return itemsList.map { QRItemMapper.entityToDomain(it) }
    }

    override suspend fun getAllFavouriteQRItems(): List<QRItem> {
        val favouriteItemsList = qrItemDao.getAllFavouriteQRItems()
        return favouriteItemsList.map { QRItemMapper.entityToDomain(it) }
    }

    override suspend fun getQRItemById(id: Int): QRItem? {
        val qrItemEntity = qrItemDao.getQRItemById(id)
        return qrItemEntity?.let { QRItemMapper.entityToDomain(it) }

    }

    override suspend fun deleteQRItem(qrItem: QRItem) {
        val qrItemEntity = QRItemMapper.domainToEntity(qrItem)
        qrItemDao.deleteQRItem(qrItemEntity.id)

    }

}