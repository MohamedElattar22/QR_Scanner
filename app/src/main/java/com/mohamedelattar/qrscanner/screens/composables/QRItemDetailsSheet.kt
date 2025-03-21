package com.mohamedelattar.qrscanner.screens.composables

import android.content.ClipData
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRItemDetailsSheet(
    modifier: Modifier = Modifier,
    qrItem: QRItem,
    onDismiss: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var copyClicked by rememberSaveable { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    val copyIconContent = when (copyClicked) {
        true -> Icons.Default.Done
        false -> ImageVector.vectorResource(R.drawable.copy_icon)
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.qr_scan_line),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)

                )

                Text(
                    "QR Details",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    "Here is your QR results",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.outline
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = qrItem.content,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp),
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier.padding(end = 16.dp),
                    ) {

                        IconButton(
                            onClick = {
                                val clipData =
                                    ClipData.newPlainText("plain text", qrItem.content)
                                val clipEntry = ClipEntry(clipData)
                                clipboardManager.setClip(clipEntry)
                                copyClicked = !copyClicked

                            },
                            modifier = Modifier.size(21.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = copyIconContent,
                                tint = MaterialTheme.colorScheme.outline,
                                contentDescription = "Share"
                            )

                        }
                        AnimatedVisibility(
                            visible = copyClicked,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                stringResource(R.string.text_copied),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline

                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

            }


        }

    }


}