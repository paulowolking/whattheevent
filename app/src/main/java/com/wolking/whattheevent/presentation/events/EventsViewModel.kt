package com.wolking.whattheevent.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.event.use_case.GetEvents
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface EventVmContract {
    val state: StateFlow<EventsState>
}

@HiltViewModel
class EventsViewModel
@Inject constructor(
    private val getEvents: GetEvents,
) : ViewModel(), EventVmContract {

    private val _state = MutableStateFlow(EventsState(isLoading = true))
    override val state: StateFlow<EventsState> = _state

    init {
        getEvents()
    }

    private fun getEvents() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            getEvents.execute(Unit).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(isLoading = false, events = it.data)
                    }
                    is Resource.Error.NoInternetConnection -> {
                        _state.value =
                            _state.value.copy(isLoading = false, hasNoInternetConnection = true)
                    }
                    is Resource.Error.RequestError,
                    is Resource.Error.GenericError -> {
                        _state.value = _state.value.copy(isLoading = false, hasGenericError = true)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}