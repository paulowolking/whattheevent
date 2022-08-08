package com.wolking.whattheevent.presentation.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wolking.whattheevent.presentation.shared.navigation.NavRoutes

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun EventsEntry(
    navController: NavHostController,
    viewModel: EventsViewModel = hiltViewModel()
) {
    EventsScreen(state = viewModel.state, onClickEvent = {
        navController.navigate(NavRoutes.Event.createRoute(it.id))
    })
}