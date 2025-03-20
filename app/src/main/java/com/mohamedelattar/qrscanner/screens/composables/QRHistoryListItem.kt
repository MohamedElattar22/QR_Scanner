package com.mohamedelattar.qrscanner.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mohamedelattar.domain.model.QRItem
import com.mohamedelattar.qrscanner.R

@Composable
fun QRHistoryListItem(
    modifier: Modifier = Modifier,
    qrItem: QRItem,
    onAddFavorite: (Boolean, QRItem) -> Unit = { _, _ ->

    },
) {
    val iconContent = when (qrItem.isFavorite) {
        true -> Icons.Default.Favorite
        false -> Icons.Outlined.FavoriteBorder
    }
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp),
        headlineContent = {

            Text(
                text = qrItem.title,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        supportingContent = {
            Text(
                text = qrItem.content,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                )


        },
        trailingContent = {
            IconButton(
                onClick = {
                    onAddFavorite(
                        !qrItem.isFavorite,
                        qrItem
                    )
                }
            ) {
                Icon(
                    imageVector = iconContent,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }



        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.qr_small),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },

        )

}
