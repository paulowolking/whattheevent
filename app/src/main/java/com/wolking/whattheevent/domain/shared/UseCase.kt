package com.wolking.whattheevent.domain.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(params: P): R {
        return withContext(dispatcher) {
            execute(params)
        }
    }

    abstract suspend fun execute(params: P): R
}
