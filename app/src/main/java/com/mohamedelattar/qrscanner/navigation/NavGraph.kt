package com.mohamedelattar.qrscanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohamedelattar.qrscanner.screens.favourite.ui.screens.FavouriteQRScreen
import com.mohamedelattar.qrscanner.screens.home.ui.screen.QRHomeScreen


@Composable
fun NavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(
            route = NavigationRoutes.QRHomeScreen.route
        ) {
            QRHomeScreen(
                navController = navController
            )
        }

        composable(
            route = NavigationRoutes.FavouritesScreen.route
        ) {
            FavouriteQRScreen(
                navController = navController
            )
        }


    }


}