package com.mohamedelattar.domain.usecase.local

import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.domain.repository.IQRScannerRepository
import javax.inject.Inject

class RemoveQRItemFromFavouriteUC @Inject constructor(
    private val qrScannerRepository: IQRScannerRepository,
) {
    suspend fun invoke(qrItem: QRItem) {
        qrScannerRepository.removeItemFromFavourite(qrItem)
    }
}