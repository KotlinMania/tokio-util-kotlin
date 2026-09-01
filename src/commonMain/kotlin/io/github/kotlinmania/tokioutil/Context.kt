// port-lint: source context.rs
package io.github.kotlinmania.tokioutil

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Context wrapper for executing tasks within a given coroutine context.
 */
class TokioContext(
    val context: CoroutineContext = EmptyCoroutineContext,
) {
    suspend fun <T> runWithContext(block: suspend () -> T): T = withContext(context) { block() }

    companion object {
        fun new(context: CoroutineContext = EmptyCoroutineContext): TokioContext = TokioContext(context)
    }
}
