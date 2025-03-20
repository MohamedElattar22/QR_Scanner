package com.mohamedelattar.domain.usecase.local

import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.repository.IQRScannerRepository
import javax.inject.Inject

class GetAllQRItemsUC @Inject constructor(
    private val repository: IQRScannerRepository,
) {
    suspend fun invoke(): List<QRItem> {
        return repository.getAllQRItems()
    }
}