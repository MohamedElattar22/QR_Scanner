package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonComp(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        modifier = modifier.padding(16.dp)

    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )

    }

}