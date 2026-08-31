// port-lint: source tokio-util/src/task/join_queue.rs
package io.github.kotlinmania.tokioutil.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * A FIFO queue of tasks spawned on a coroutine scope.
 */
internal class JoinQueue<T> {
    private val queue = ArrayDeque<Deferred<T>>()

    fun len(): Int = queue.size

    fun isEmpty(): Boolean = queue.isEmpty()

    fun spawn(scope: CoroutineScope, block: suspend CoroutineScope.() -> T) {
        val deferred = scope.async { block() }
        queue.addLast(deferred)
    }

    suspend fun joinNext(): Result<T>? {
        val next = queue.removeFirstOrNull() ?: return null
        return try {
            Result.success(next.await())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    fun abortAll() {
        while (queue.isNotEmpty()) {
            queue.removeFirst().cancel()
        }
    }

    companion object {
        fun <T> new(): JoinQueue<T> = JoinQueue()
    }
}
