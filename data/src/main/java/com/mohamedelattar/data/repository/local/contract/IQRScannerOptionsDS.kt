package com.mohamedelattar.data.repository.local.contract

import com.journeyapps.barcodescanner.ScanOptions

interface IQRScannerOptionsDS {
    suspend fun initializeQRScanner(): ScanOptions
}