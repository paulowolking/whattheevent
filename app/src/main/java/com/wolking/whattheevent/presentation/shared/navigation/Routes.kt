package com.wolking.whattheevent.presentation.shared.navigation

sealed class NavRoutes(val route: String) {
    object Events : NavRoutes("events")
    object Event : NavRoutes("event/{id}") {
        fun createRoute(id: String) = "event/$id"
    }
}