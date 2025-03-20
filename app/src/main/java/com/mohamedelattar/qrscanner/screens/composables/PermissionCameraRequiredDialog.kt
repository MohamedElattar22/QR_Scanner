package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mohamedelattar.qrscanner.R

@Composable
fun PermissionCameraRequired(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        content = {
            Box(
                modifier = modifier
                    .size(300.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.noto_v1_camera),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    Text(
                        text = "permission required !",

                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 21.sp
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "In order to run the QR Scanner you should\n" +
                                "  give us the permission for camera",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        color = MaterialTheme.colorScheme.outline
                    )

                }


            }

        }
    )

}

@Preview
@Composable
private fun saPreview() {
    PermissionCameraRequired()

}