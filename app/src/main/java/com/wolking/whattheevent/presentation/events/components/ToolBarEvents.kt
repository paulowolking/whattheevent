package com.wolking.whattheevent.presentation.events.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ToolBarEvents(title: String) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(title)
            },
            backgroundColor = MaterialTheme.colors.primarySurface
        )
    }
}