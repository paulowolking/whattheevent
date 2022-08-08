package com.wolking.whattheevent.presentation.event_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ToolBarEventDetail(
    title: String,
    onClickBackButton: () -> Unit,
    onClickShare: () -> Unit,
    onClickCheckIn: () -> Unit
) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(title)
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = onClickBackButton) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = onClickShare) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = onClickCheckIn) {
                    Icon(Icons.Filled.Star, null)
                }
            })
    }
}