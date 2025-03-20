package com.mohamedelattar.data.di

import com.journeyapps.barcodescanner.ScanOptions
import com.mohamedelattar.data.common.local.room.dao.QRItemDao
import com.mohamedelattar.data.repository.QRScannerRepository
import com.mohamedelattar.data.repository.local.contract.IQRScannerLocalDS
import com.mohamedelattar.data.repository.local.contract.IQRScannerOptionsDS
import com.mohamedelattar.data.repository.local.implementation.QRScannerLocalDS
import com.mohamedelattar.data.repository.local.implementation.QRScannerOptionsDS
import com.mohamedelattar.domain.repository.IQRScannerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QRScannerModule {

    @Singleton
    @Provides
    fun providesQRScannerOptions(): ScanOptions {
        return ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan your QR")
            setCameraId(0)
            setBeepEnabled(false)
            setBarcodeImageEnabled(true)
            setOrientationLocked(false)
        }
    }

    @Provides
    fun providesQRScannerDataSource(
        scanOptions: ScanOptions,
    ): IQRScannerOptionsDS {
        return QRScannerOptionsDS(scanOptions)
    }


    @Provides
    fun providesQRScannerLocalDS(
        qrItemDao: QRItemDao,
    ): IQRScannerLocalDS {
        return QRScannerLocalDS(qrItemDao)
    }

    @Provides
    fun providesQRScannerRepository(
        qrScannerLocalDS: IQRScannerLocalDS,
        qrScannerOptionsDS: IQRScannerOptionsDS,
    ): IQRScannerRepository {
        return QRScannerRepository(qrScannerLocalDS, qrScannerOptionsDS)

    }


}