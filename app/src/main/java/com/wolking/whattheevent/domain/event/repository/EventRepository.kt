package com.wolking.whattheevent.domain.event.repository

import com.wolking.whattheevent.data.core.Resource
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.domain.event.entities.User
import kotlinx.coroutines.flow.Flow

abstract class EventRepository {
    abstract suspend fun getEvents(): Flow<Resource<List<Event>>>
    abstract suspend fun getEventById(id: Int): Flow<Resource<Event>>
    abstract suspend fun checkIn(user: User): Flow<Resource<Boolean>>
}