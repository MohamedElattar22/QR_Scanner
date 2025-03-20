package com.mohamedelattar.domain.model

data class QRItem(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val isFavorite: Boolean,
)
