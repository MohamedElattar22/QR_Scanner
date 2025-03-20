package com.mohamedelattar.data.common.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohamedelattar.data.common.local.room.dao.QRItemDao
import com.mohamedelattar.data.model.entity.QRItemEntity

@Database(
    entities = [QRItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QRScannerDatabase : RoomDatabase() {
    abstract fun qrItemDao(): QRItemDao

}