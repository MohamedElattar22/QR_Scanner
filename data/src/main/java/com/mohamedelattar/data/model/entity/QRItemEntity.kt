package com.mohamedelattar.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_items")
data class QRItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val isFavorite: Boolean,
)
