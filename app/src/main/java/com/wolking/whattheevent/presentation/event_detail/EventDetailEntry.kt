package com.wolking.whattheevent.presentation.event_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wolking.whattheevent.R
import com.wolking.whattheevent.presentation.events.EventsViewModel
import com.wolking.whattheevent.presentation.shared.components.Empty
import com.wolking.whattheevent.presentation.shared.screens.GenericErrorScreen

@ExperimentalMaterialApi
@Composable
fun EventDetailEntry(
    navController: NavHostController,
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    if (viewModel.state.collectAsState().value.hasNoParameterScreen) {
        GenericErrorScreen(
            stringResource(id = R.string.event_detail),
            onClickBackButton = { navController.popBackStack() })
    } else {
        EventDetailScreen(state = viewModel.state, onClickBackButton = {
            navController.popBackStack()
        }, onClickCheckIn = {
            viewModel.checkIn()
        })
    }
}