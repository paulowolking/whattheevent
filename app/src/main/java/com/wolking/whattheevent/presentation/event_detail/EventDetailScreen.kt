package com.wolking.whattheevent.presentation.event_detail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.wolking.whattheevent.R
import com.wolking.whattheevent.presentation.event_detail.components.DialogCheckIn
import com.wolking.whattheevent.presentation.event_detail.components.EventDetail
import com.wolking.whattheevent.presentation.event_detail.components.ToolBarEventDetail
import com.wolking.whattheevent.presentation.shared.components.Empty
import com.wolking.whattheevent.presentation.shared.components.Error
import com.wolking.whattheevent.presentation.shared.components.Loading
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(
    state: StateFlow<EventDetailState>,
    onClickBackButton: () -> Unit,
    onClickCheckIn: () -> Unit,
) {
    val uiState by state.collectAsState()
    val event = uiState.event
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ToolBarEventDetail(
                title = stringResource(R.string.event_detail),
                onClickBackButton = onClickBackButton,
                onClickShare = { share(context, event?.description ?: "") },
                onClickCheckIn = {
                    uiState.showDialogCheckIn.value = true
                    uiState.showMessageSuccessCheckIn.value = false
                }
            )
        },
        content = {
            if (uiState.isLoading) {
                Loading()
            } else {
                if (uiState.hasNoInternetConnection) {
                    Error(message = stringResource(R.string.no_connection))
                } else if (uiState.hasGenericError) {
                    Empty(message = stringResource(R.string.occurrence_error))
                } else {
                    event?.let {
                        if (uiState.showDialogCheckIn.value) {
                            DialogCheckIn(
                                name = uiState.name,
                                email = uiState.email,
                                onDismiss = { uiState.showDialogCheckIn.value = false },
                                onConfirm = {
                                    uiState.showDialogCheckIn.value = false
                                    onClickCheckIn()
                                })
                        }

                        if (uiState.showMessageSuccessCheckIn.value) {
                            Toast.makeText(
                                context,
                                stringResource(R.string.check_in_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        EventDetail(event = it)
                    }
                }
            }
        }
    )
}

private fun share(context: Context, message: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(context, shareIntent, null)
}