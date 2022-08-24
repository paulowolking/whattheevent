package com.wolking.whattheevent.presentation.events

import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.data.shared.CoroutineDispatchersImpl
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.event.use_case.GetEvents
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest {
    @Mock
    private lateinit var eventRepository: EventRepository

    private lateinit var viewModel: EventsViewModel

    @Inject
    private lateinit var coroutineDispatchers: CoroutineDispatchers

    @Before
    fun setup() {
        coroutineDispatchers = CoroutineDispatchersImpl()
    }

    @Test
    fun checkInitialValuesCreateViewModel() = runBlocking {
        val getEvents = GetEvents(eventRepository, coroutineDispatchers)
        viewModel = EventsViewModel(getEvents)
        val state = viewModel.state.value
        assert(state.events.isEmpty())
        assert(state.isLoading)
        assert(!state.hasGenericError)
        assert(!state.hasNoInternetConnection)
        assert(!state.hasNoParameterScreen)
    }

    @Test
    fun useCaseSetEventsStateTest() = runBlocking {
        val getEvents = GetEvents(eventRepository, coroutineDispatchers)
        val fakeEvent = Event(
            id = "0",
            date = 0,
            description = "",
            image = "",
            longitude = 0.0,
            latitude = 0.0,
            price = 0.0,
            title = ""
        )
        val events = listOf(fakeEvent)

        `when`(eventRepository.getEvents()).thenReturn(flowOf(Resource.Success(events)))
        viewModel = EventsViewModel(getEvents)

        val state = viewModel.state.value

        if (!state.isLoading)
            assert(state.events.isNotEmpty())
    }
}