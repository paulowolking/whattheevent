package com.wolking.whattheevent.presentation.shared.navigation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.wolking.whattheevent.presentation.event_detail.EventDetailEntry
import com.wolking.whattheevent.presentation.events.EventsEntry

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Events.route,
    ) {
        composable(NavRoutes.Events.route) {
            EventsEntry(navController = navController)
        }

        composable(NavRoutes.Event.route) {
            EventDetailEntry(navController = navController)
        }
    }
}