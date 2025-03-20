package com.mohamedelattar.qrscanner.screens.favourite.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohamedelattar.qrscanner.screens.composables.FloatingActionButtonComp
import com.mohamedelattar.qrscanner.screens.composables.TopAppBarComp

@Composable
fun FavouriteQRScreen(
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarComp(
                hasNavigation = true,
                title = "Favourites",
                onNavigation = {

                }
            )
        },
        floatingActionButton = {
            FloatingActionButtonComp(
                onClick = {

                }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {


        }


    }
}