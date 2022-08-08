package com.wolking.whattheevent.data.core

import com.wolking.whattheevent.data.event.models.EventDto
import com.wolking.whattheevent.domain.event.entities.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("events")
    suspend fun getEvents(): List<EventDto>

    @GET("events/{id}")
    suspend fun getEvent(
        @Path("id") id: Int
    ): EventDto

    @POST("checkin")
    suspend fun checkIn(
        @Body user: User
    )
}