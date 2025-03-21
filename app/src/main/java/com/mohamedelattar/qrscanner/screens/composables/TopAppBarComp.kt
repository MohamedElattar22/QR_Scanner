package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComp(
    modifier: Modifier = Modifier,
    title: String = "",
    hasAction: Boolean = false,
    hasNavigation: Boolean = false,
    onAction: () -> Unit = {},
    onNavigation: () -> Unit = {},
) {
    CenterAlignedTopAppBar(

        modifier = modifier,
        title = {
            Text(
                text = title
            )
        },
        actions = {
            if (hasAction) {
                IconButton(
                    onClick = {
                        onAction()
                    }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = ImageVector.vectorResource(id = com.mohamedelattar.qrscanner.R.drawable.fav_ic),
                        contentDescription = null
                    )
                }
            }

        },
        navigationIcon = {
            if (hasNavigation) {
                IconButton(
                    onClick = {
                        onNavigation()
                    }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }

        }
    )
}
