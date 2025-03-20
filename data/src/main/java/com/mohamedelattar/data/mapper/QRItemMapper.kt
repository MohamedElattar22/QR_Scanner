package com.mohamedelattar.data.mapper

import com.mohamedelattar.data.model.entity.QRItemEntity
import com.mohamedelattar.domain.model.QRItem

object QRItemMapper {

    fun entityToDomain(entity: QRItemEntity): QRItem {
        return QRItem(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            isFavorite = entity.isFavorite,
            createdAt = entity.createdAt,
        )
    }

    fun domainToEntity(domain: QRItem): QRItemEntity {
        return QRItemEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            isFavorite = domain.isFavorite,
            createdAt = domain.createdAt,
        )
    }
}