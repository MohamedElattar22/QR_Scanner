package com.mohamedelattar.data.common.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohamedelattar.data.model.entity.QRItemEntity

@Dao
interface QRItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQRItem(qrItem: QRItemEntity)

    @Query("SELECT * FROM qr_items")
    suspend fun getAllQRItems(): List<QRItemEntity>

    @Query("SELECT * FROM qr_items WHERE id = :id")
    suspend fun getQRItemById(id: Int): QRItemEntity?

    @Query("SELECT * FROM qr_items WHERE isFavorite = 1")
    suspend fun getAllFavouriteQRItems(): List<QRItemEntity>

    @Query("UPDATE qr_items SET isFavorite = 1 WHERE id = :id")
    suspend fun addItemToFavourite(id: Int)

    @Query("UPDATE qr_items SET isFavorite = 0 WHERE id = :id")
    suspend fun removeItemFromFavourite(id: Int)

    @Query("DELETE FROM qr_items WHERE id = :id")
    suspend fun deleteQRItem(id: Int)

}