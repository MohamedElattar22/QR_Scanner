package com.mohamedelattar.qrscanner.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohamedelattar.qrscanner.screens.home.ui.screen.ScannerScreen
import com.mohamedelattar.qrscanner.ui.theme.QRScannerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRScannerAppTheme {
                ScannerScreen()
            }
        }
    }
}