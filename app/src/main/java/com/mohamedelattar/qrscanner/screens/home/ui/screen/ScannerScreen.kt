package com.mohamedelattar.qrscanner.screens.home.ui.screen

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.journeyapps.barcodescanner.ScanContract
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.screens.composables.FloatingActionButtonComp
import com.mohamedelattar.qrscanner.screens.composables.ScannedQRContentSheet
import com.mohamedelattar.qrscanner.screens.composables.TabsComposable
import com.mohamedelattar.qrscanner.screens.composables.TopAppBarComp
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scanOptions by viewModel.scanOptions.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val qrCodeLauncher = rememberLauncherForActivityResult(
        ScanContract()
    ) { result ->

        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            viewModel.onAction(
                HomeContract.QRScannerActions.InsertQRItem(
                    QRItem(
                        title = "test",
                        content = result.contents,
                        createdAt = System.currentTimeMillis(),
                        isFavorite = false,
                        id = 0
                    )
                )
            )

            viewModel.onAction(HomeContract.QRScannerActions.ShowItemDataSheet(true))

        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onAction(HomeContract.QRScannerActions.InitializeQRScanner)
        } else {

        }

    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                hasAction = true,
                title = stringResource(com.mohamedelattar.qrscanner.R.string.qrscanner),
                onAction = {

                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonComp(
                onClick = {
                    qrCodeLauncher.launch(
                        scanOptions
                    )
                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            TabsComposable(
                state = state,
                onAction = viewModel::onAction
            )

            if (state.showItemDataSheet) {
                ScannedQRContentSheet(
                    qrContent = state.qrContent,
                    onDismiss = {
                        viewModel.onAction(
                            HomeContract.QRScannerActions.ShowItemDataSheet(false)
                        )
                    }
                )
            }

        }

    }

}