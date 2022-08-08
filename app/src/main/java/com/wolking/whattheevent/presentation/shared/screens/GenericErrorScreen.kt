package com.wolking.whattheevent.presentation.shared.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.wolking.whattheevent.R
import com.wolking.whattheevent.presentation.shared.components.Empty
import com.wolking.whattheevent.presentation.shared.components.ToolBarDefault

@ExperimentalMaterialApi
@Composable
fun GenericErrorScreen(
    textToolbar: String,
    onClickBackButton: () -> Unit
) {
    Scaffold(
        topBar = {
            ToolBarDefault(
                title = textToolbar,
                onClickBackButton = onClickBackButton,
            )
        },
        content = {
            Empty(message = stringResource(id = R.string.occurrence_error))
        }
    )
}