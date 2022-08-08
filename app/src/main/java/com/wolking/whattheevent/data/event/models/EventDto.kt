package com.wolking.whattheevent.data.event.models

import com.wolking.whattheevent.domain.event.entities.Event

data class EventDto(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
) {
    fun toDomain(): Event {
        return Event(
            id = id,
            date = date,
            description = description,
            image = image,
            latitude = latitude,
            longitude = longitude,
            price = price,
            title = title
        )
    }
}