package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mohamedelattar.qrscanner.R

@Composable
fun SettingDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.permission_required)) },
        text = { Text(stringResource(R.string.setting_description)) },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()

            }) {
                Text(stringResource(R.string.open_settings))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )

}