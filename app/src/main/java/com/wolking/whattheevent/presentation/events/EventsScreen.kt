package com.wolking.whattheevent.presentation.events

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wolking.whattheevent.R
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.presentation.events.components.EventRow
import com.wolking.whattheevent.presentation.events.components.ToolBarEvents
import com.wolking.whattheevent.presentation.shared.components.Empty
import com.wolking.whattheevent.presentation.shared.components.Error
import com.wolking.whattheevent.presentation.shared.components.Loading
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterialApi
@Composable
fun EventsScreen(
    state: StateFlow<EventsState>,
    onClickEvent: (event: Event) -> Unit
) {
    val uiState by state.collectAsState()

    Scaffold(
        topBar = {
            ToolBarEvents(
                title = stringResource(R.string.event_list),
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
                    LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)) {
                        items(uiState.events) { event ->
                            EventRow(event = event, onClickEvent = { onClickEvent(event) })
                        }
                    }
                }
            }
        }
    )
}
