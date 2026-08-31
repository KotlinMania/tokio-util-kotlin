// port-lint: source tokio-util/src/task/join_map.rs
@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package io.github.kotlinmania.tokioutil.task

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * A collection of tasks spawned on a coroutine scope, associated with hash map keys.
 */
internal class JoinMap<K, V> {
    private val tasks = mutableMapOf<K, Deferred<V>>()

    fun len(): Int = tasks.size

    fun isEmpty(): Boolean = tasks.isEmpty()

    fun containsKey(key: K): Boolean = tasks.containsKey(key)

    fun spawn(scope: CoroutineScope, key: K, block: suspend CoroutineScope.() -> V) {
        val deferred = scope.async { block() }
        tasks[key] = deferred
    }

    fun abort(key: K): Boolean {
        val task = tasks.remove(key) ?: return false
        task.cancel()
        return true
    }

    fun abortMatching(predicate: (K) -> Boolean): Int {
        val matchingKeys = tasks.keys.filter(predicate)
        for (key in matchingKeys) {
            tasks.remove(key)?.cancel()
        }
        return matchingKeys.size
    }

    fun abortAll() {
        for (task in tasks.values) {
            task.cancel()
        }
        tasks.clear()
    }

    suspend fun joinNext(): Pair<K, Result<V>>? {
        if (tasks.isEmpty()) return null
        val completer = CompletableDeferred<Pair<K, Result<V>>>()
        for ((key, deferred) in tasks) {
            deferred.invokeOnCompletion { cause ->
                if (cause == null) {
                    val res =
                        try {
                            Result.success(deferred.getCompleted())
                        } catch (e: Throwable) {
                            Result.failure(e)
                        }
                    completer.complete(Pair(key, res))
                } else {
                    completer.complete(Pair(key, Result.failure(cause)))
                }
            }
        }
        val result = completer.await()
        tasks.remove(result.first)
        return result
    }

    companion object {
        fun <K, V> new(): JoinMap<K, V> = JoinMap()
    }
}
