package com.mohamedelattar.qrscanner.navigation

sealed class NavigationRoutes(val route: String) {
    object QRHomeScreen : NavigationRoutes("QR_home_screen")
    object FavouritesScreen : NavigationRoutes("favourites_screen")
}