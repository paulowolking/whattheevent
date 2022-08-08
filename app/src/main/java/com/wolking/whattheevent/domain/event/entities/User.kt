package com.wolking.whattheevent.domain.event.entities

data class User(
    val eventId: String,
    val name: String,
    val email: String
)