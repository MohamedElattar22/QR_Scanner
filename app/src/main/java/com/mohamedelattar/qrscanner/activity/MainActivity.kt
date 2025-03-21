package com.mohamedelattar.qrscanner.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mohamedelattar.qrscanner.navigation.NavGraph
import com.mohamedelattar.qrscanner.navigation.NavigationRoutes
import com.mohamedelattar.qrscanner.ui.theme.QRScannerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            QRScannerAppTheme {
                NavGraph(
                    startDestination = NavigationRoutes.QRHomeScreen.route,
                    navController = navController
                )
            }
        }
    }
}