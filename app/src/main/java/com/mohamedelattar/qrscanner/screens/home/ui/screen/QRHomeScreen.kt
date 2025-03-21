package com.mohamedelattar.qrscanner.screens.home.ui.screen

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.journeyapps.barcodescanner.ScanContract
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.R
import com.mohamedelattar.qrscanner.activity.findActivity
import com.mohamedelattar.qrscanner.navigation.NavigationRoutes
import com.mohamedelattar.qrscanner.screens.composables.FloatingActionButtonComp
import com.mohamedelattar.qrscanner.screens.composables.RationaleDialog
import com.mohamedelattar.qrscanner.screens.composables.ScannedQRContentSheet
import com.mohamedelattar.qrscanner.screens.composables.SettingDialog
import com.mohamedelattar.qrscanner.screens.composables.TabsComposable
import com.mohamedelattar.qrscanner.screens.composables.TopAppBarComp
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeContract
import com.mohamedelattar.qrscanner.screens.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRHomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scanOptions by viewModel.scanOptions.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context.findActivity()


    val qrCodeLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result ->

        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {

            viewModel.onAction(
                HomeContract.QRScannerActions.InsertQRItem(
                    QRItem(
                        title = "title",
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
        }
    }

    val checkAndRequestPermission = {
        when {
            ContextCompat.checkSelfPermission(
                context,
                permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.onAction(HomeContract.QRScannerActions.InitializeQRScanner)
                qrCodeLauncher.launch(
                    scanOptions
                )
            }

            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.CAMERA) -> {
                viewModel.onAction(HomeContract.QRScannerActions.ShowRationaleDialog(true))
            }

            else -> {
                launcher.launch(permission.CAMERA)
            }
        }
    }



    LaunchedEffect(Unit) {
        launcher.launch(permission.CAMERA)
        viewModel.onAction(HomeContract.QRScannerActions.GetAllQRItems)
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                hasAction = true,
                title = stringResource(R.string.qrscanner),
                onAction = {
                    navController.navigate(NavigationRoutes.FavouritesScreen.route)

                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonComp(
                onClick = {
                    val permanentlyDenied =
                        !ActivityCompat.shouldShowRequestPermissionRationale(
                            activity,
                            permission.CAMERA
                        ) &&
                                ContextCompat.checkSelfPermission(
                                    context,
                                    permission.CAMERA
                                ) != PackageManager.PERMISSION_GRANTED

                    if (permanentlyDenied) {
                        viewModel.onAction(
                            HomeContract.QRScannerActions.ShowSettingsDialog(true)
                        )
                    } else {
                        checkAndRequestPermission()
                    }

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

        if (state.showSettingsDialog) {
            SettingDialog(
                onDismiss = {
                    viewModel.onAction(
                        HomeContract.QRScannerActions.ShowSettingsDialog(false)
                    )
                },
                onConfirm = {
                    viewModel.onAction(
                        HomeContract.QRScannerActions.ShowSettingsDialog(false)
                    )
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)

                }


            )
        }

        if (state.showRationaleDialog) {
            RationaleDialog(
                onDismiss = {
                    viewModel.onAction(
                        HomeContract.QRScannerActions.ShowRationaleDialog(false)
                    )
                },
                onConfirm = {
                    launcher.launch(permission.CAMERA)
                    viewModel.onAction(
                        HomeContract.QRScannerActions.ShowRationaleDialog(false)
                    )
                }
            )
        }

    }

}