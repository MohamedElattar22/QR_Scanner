package com.mohamedelattar.data.common.local.room.module

import android.content.Context
import androidx.room.Room
import com.mohamedelattar.data.common.local.room.dao.QRItemDao
import com.mohamedelattar.data.common.local.room.database.QRScannerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): QRScannerDatabase {
        return Room.databaseBuilder(
            context,
            QRScannerDatabase::class.java,
            "qr_scanner_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesQRItemDao(
        database: QRScannerDatabase,
    ): QRItemDao {
        return database.qrItemDao()
    }


}