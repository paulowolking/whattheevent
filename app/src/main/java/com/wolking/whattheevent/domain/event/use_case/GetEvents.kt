package com.wolking.whattheevent.domain.event.use_case

import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import com.wolking.whattheevent.domain.shared.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEvents @Inject constructor(
    private val eventRepository: EventRepository,
    coroutineDispatchers: CoroutineDispatchers
) : UseCase<Unit, Flow<Resource<List<Event>>>>(coroutineDispatchers.ioContext as CoroutineDispatcher) {

    override suspend fun execute(params: Unit): Flow<Resource<List<Event>>> =
        eventRepository.getEvents()
}