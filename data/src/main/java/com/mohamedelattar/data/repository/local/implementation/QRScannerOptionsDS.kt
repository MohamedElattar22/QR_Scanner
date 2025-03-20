package com.mohamedelattar.data.repository.local.implementation

import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.data.repository.local.contract.IQRScannerOptionsDS
import javax.inject.Inject

class QRScannerOptionsDS @Inject constructor(
    private val scanOptions: ScanOptions,
) : IQRScannerOptionsDS {

    override suspend fun initializeQRScanner(): ScanOptions {
        return scanOptions
    }

}