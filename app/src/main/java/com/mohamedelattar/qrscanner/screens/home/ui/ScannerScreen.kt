package com.mohamedelattar.qrscanner.screens.home.ui

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.journeyapps.barcodescanner.ScanContract
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.screens.composables.ScannedQRContentSheet
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeViewModel

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    qrCodeLauncher.launch(
                        scanOptions
                    )
                },
                modifier = Modifier.padding(16.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.showItemDataSheet) {
                ScannedQRContentSheet(
                    qrContent = state.qrContent,
                    onDismiss = {
                        viewModel.onAction(HomeContract.QRScannerActions.ShowItemDataSheet(false))
                    }

                )
            }


        }

    }

}