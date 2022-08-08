package com.wolking.whattheevent.presentation.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ToolBarDefault(
    title: String,
    onClickBackButton: () -> Unit,
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

            })
    }
}