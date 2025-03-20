package com.mohamedelattar.qrscanner.screens.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedelattar.qrscanner.R

@Composable
fun QRScanScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.scan_home),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.height(50.dp)
        )

        Text(
            text = stringResource(R.string.scan_qr_code),
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = stringResource(R.string.warning_guide_msg),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            color = MaterialTheme.colorScheme.outline

        )

    }

}