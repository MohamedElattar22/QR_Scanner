package com.mohamedelattar.data

import com.mohamedelattar.data.common.local.room.dao.QRItemDao
import com.mohamedelattar.data.mapper.QRItemMapper
import com.mohamedelattar.data.model.entity.QRItemEntity
import com.mohamedelattar.data.repository.local.implementation.QRScannerLocalDS
import com.mohamedelattar.domain.model.QRItem
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class QRItemLocalDataSourceTest {
    private lateinit var qrScannerLocalDS: QRScannerLocalDS

    @MockK
    private lateinit var qrItemDao: QRItemDao

    @Before
    fun setup() {
        qrItemDao = mockk()
        qrScannerLocalDS = QRScannerLocalDS(qrItemDao)
    }

    @Test
    fun `getQRItems calls returns items list with correct entity`() = runTest {
        val expectedId = 1

        val qrItemEntity = QRItemEntity(
            id = expectedId,
            title = "test",
            content = "test data",
            createdAt = 1,
            isFavorite = false
        )
        val qrItem = QRItem(
            id = expectedId,
            title = "test",
            content = "test data",
            createdAt = 1,
            isFavorite = false
        )
        coEvery { qrItemDao.getAllQRItems() } returns listOf(qrItemEntity)
        val actual = qrScannerLocalDS.getAllQRItems()

        assertEquals(listOf(qrItem), actual)
    }


    @Test
    fun `getAllQRItems returns correctly mapped list`() = runTest {

        val domainList = listOf(
            QRItem(
                id = 1,
                title = "test1",
                content = "test data 1 ",
                createdAt = System.currentTimeMillis(),
                isFavorite = false
            ),
            QRItem(
                id = 2,
                title = "test2",
                content = "test data2",
                createdAt = System.currentTimeMillis(),
                isFavorite = true
            )
        )
        val entityList = domainList.map { QRItemMapper.domainToEntity(it) }
        coEvery { qrItemDao.getAllQRItems() } returns entityList


        val result = qrScannerLocalDS.getAllQRItems()

        val expected = entityList.map { QRItemMapper.entityToDomain(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `getQRItemById returns correct QRItem when found`() = runTest {

        val id = 2
        val domainItem = QRItem(
            id = id,
            title = "test2",
            content = "test data2",
            createdAt = System.currentTimeMillis(),
            isFavorite = true
        )
        val entity = QRItemMapper.domainToEntity(domainItem)
        coEvery { qrItemDao.getQRItemById(id) } returns entity

        // When
        val result = qrScannerLocalDS.getQRItemById(id)


        val expected = QRItemMapper.entityToDomain(entity)
        assertEquals(expected, result)
    }

    @Test
    fun `getAllFavouriteQRItems returns correctly mapped list`() = runBlocking {

        val domainFavList = listOf(
            QRItem(
                id = 2,
                title = "test2",
                content = "test data2",
                createdAt = 2,
                isFavorite = true
            ),
            QRItem(
                id = 3,
                title = "test3",
                content = "test data3",
                createdAt = 3,
                isFavorite = true
            )
        )
        val favEntityList = domainFavList.map { QRItemMapper.domainToEntity(it) }
        coEvery { qrItemDao.getAllFavouriteQRItems() } returns favEntityList

        val result = qrScannerLocalDS.getAllFavouriteQRItems()

        val expected = favEntityList.map { QRItemMapper.entityToDomain(it) }
        assertEquals(expected, result)
    }

}