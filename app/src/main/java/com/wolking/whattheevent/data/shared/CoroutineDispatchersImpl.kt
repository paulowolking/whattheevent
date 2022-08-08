package com.wolking.whattheevent.data.shared

import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
    override val ioContext: CoroutineContext
        get() = Dispatchers.IO
    override val uiContext: CoroutineContext
        get() = Dispatchers.Main
    override val defaultContext: CoroutineContext
        get() = Dispatchers.Default
}