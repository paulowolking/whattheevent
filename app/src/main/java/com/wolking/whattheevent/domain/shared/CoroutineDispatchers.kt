package com.wolking.whattheevent.domain.shared

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val ioContext: CoroutineContext
    val uiContext: CoroutineContext
    val defaultContext: CoroutineContext
}