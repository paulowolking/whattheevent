package com.wolking.whattheevent.presentation.events

import com.wolking.whattheevent.presentation.shared.ErrorState
import com.wolking.whattheevent.domain.event.entities.Event

data class EventsState(
    val isLoading: Boolean = false,
    val events: List<Event> = arrayListOf(),
    override val hasNoInternetConnection: Boolean = false,
    override val hasGenericError: Boolean = false,
    override val hasNoParameterScreen: Boolean = false
) : ErrorState