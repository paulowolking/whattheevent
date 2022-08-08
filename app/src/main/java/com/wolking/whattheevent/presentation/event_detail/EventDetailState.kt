package com.wolking.whattheevent.presentation.event_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.wolking.whattheevent.presentation.shared.ErrorState
import com.wolking.whattheevent.domain.event.entities.Event

data class EventDetailState(
    val isLoading: Boolean = false,
    val event: Event? = null,
    var showDialogCheckIn: MutableState<Boolean> = mutableStateOf(false),
    var name: MutableState<String> = mutableStateOf(""),
    var email: MutableState<String> = mutableStateOf(""),
    val showMessageSuccessCheckIn: MutableState<Boolean> = mutableStateOf(false),
    override val hasNoInternetConnection: Boolean = false,
    override val hasGenericError: Boolean = false,
    override val hasNoParameterScreen: Boolean = false
) : ErrorState