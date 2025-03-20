package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mohamedelattar.qrscanner.screens.home.ui.screen.QRHistoryScreen
import com.mohamedelattar.qrscanner.screens.home.ui.screen.QRScanScreen
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsComposable(
    modifier: Modifier = Modifier,
    state: HomeContract.QRScannerState,
    onAction: (HomeContract.QRScannerActions) -> Unit = {},
) {
    val pagerState = rememberPagerState(
        pageCount = {
            state.tabs.size
        }, initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    PrimaryTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier,
    ) {
        state.tabs.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = title,
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 16.sp,
                    )


                }
            )
        }
    }

    HorizontalPager(
        state = pagerState,
    ) { page ->

        when (page) {
            0 -> {
                QRScanScreen()
            }

            1 -> {
                QRHistoryScreen(
                    state = state,
                    onAction = onAction
                )
            }
        }
    }


}