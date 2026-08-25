// port-lint: source task/spawn_pinned.rs
package io.github.kotlinmania.tokioutil.task

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

/**
 * A handle for spawning pinned asynchronous tasks.
 */
internal class LocalPoolHandle(
    val poolSize: Int = 1,
    context: CoroutineContext = Dispatchers.Default,
) {
    private val scope = CoroutineScope(context)

    fun numThreads(): Int = poolSize

    fun <T> spawnPinned(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return scope.async { block() }
    }

    companion object {
        fun new(poolSize: Int): LocalPoolHandle {
            require(poolSize > 0) { "pool size must be greater than 0" }
            return LocalPoolHandle(poolSize)
        }
    }
}

