package com.wolking.whattheevent.presentation.event_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.domain.event.entities.User
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.event.use_case.GetEvent
import com.wolking.whattheevent.domain.event.use_case.SetCheckIn
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface EventDetailVmContract {
    val state: StateFlow<EventDetailState>
}

@HiltViewModel
class EventDetailViewModel
@Inject constructor(
    private val getEvent: GetEvent,
    private val setCheckIn: SetCheckIn,
    savedStateHandle: SavedStateHandle
) : ViewModel(), EventDetailVmContract {

    private val _state = MutableStateFlow(EventDetailState(isLoading = true))
    override val state: StateFlow<EventDetailState> = _state

    init {
        val eventId = savedStateHandle.get<String>("id")
        if (eventId == null) {
            _state.value = state.value.copy(hasNoParameterScreen = true)
        } else {
            getEvent(eventId.toInt())
        }
    }

    private fun getEvent(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getEvent.execute(id).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(isLoading = false, event = it.data)
                    }
                    is Resource.Error.NoInternetConnection -> {
                        _state.value =
                            _state.value.copy(isLoading = false, hasNoInternetConnection = true)
                    }
                    is Resource.Error.RequestError,
                    is Resource.Error.GenericError -> {
                        _state.value = _state.value.copy(isLoading = false, hasGenericError = true)
                    }
                    else -> {}
                }
            }
        }
    }

    fun checkIn() {
        viewModelScope.launch {
            val uiState = _state.value
            val user = User(
                eventId = uiState.event?.id.toString(),
                name = uiState.name.value,
                email = uiState.email.value
            )

            _state.value = _state.value.copy(
                isLoading = true,
                showMessageSuccessCheckIn = mutableStateOf(false)
            )
            setCheckIn.execute(user).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value =
                            _state.value.copy(
                                isLoading = false,
                                showMessageSuccessCheckIn = mutableStateOf(true)
                            )
                    }
                    is Resource.Error.NoInternetConnection -> {
                        _state.value =
                            _state.value.copy(isLoading = false, hasNoInternetConnection = true)
                    }
                    is Resource.Error.RequestError,
                    is Resource.Error.GenericError -> {
                        _state.value = _state.value.copy(isLoading = false, hasGenericError = true)
                    }
                    else -> {}
                }
            }
        }
    }
}