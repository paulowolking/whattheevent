package com.wolking.whattheevent.presentation.shared

interface ErrorState {
    val hasNoInternetConnection: Boolean
    val hasGenericError: Boolean
    val hasNoParameterScreen: Boolean
}