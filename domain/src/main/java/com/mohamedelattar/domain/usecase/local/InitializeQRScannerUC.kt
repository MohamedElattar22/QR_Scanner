package com.mohamedelattar.domain.usecase.local

import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.domain.repository.IQRScannerRepository
import javax.inject.Inject

class InitializeQRScannerUC @Inject constructor(
    private val qrScannerRepository: IQRScannerRepository,
) {
    suspend fun invoke(): ScanOptions {
        return qrScannerRepository.initializeQRScanner()
    }
}