// port-lint: source task/task_tracker.rs
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.github.kotlinmania.tokioutil.task

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicInt

/**
 * A task tracker used for waiting until tasks exit.
 */
class TaskTracker {
    private val taskCount = AtomicInt(0)
    private val isClosed = AtomicBoolean(false)
    private val exitNotifier = CompletableDeferred<Unit>()

    fun close(): Boolean {
        val wasClosed = isClosed.exchange(true)
        if (!wasClosed && taskCount.load() == 0) {
            exitNotifier.complete(Unit)
        }
        return !wasClosed
    }

    fun reopen(): Boolean {
        val wasClosed = isClosed.exchange(false)
        return wasClosed
    }

    fun isClosed(): Boolean = isClosed.load()

    fun isEmpty(): Boolean = taskCount.load() == 0

    fun len(): Int = taskCount.load()

    fun token(): TaskTrackerToken {
        taskCount.addAndFetch(1)
        return TaskTrackerToken(this)
    }

    internal fun dropTask() {
        val remaining = taskCount.addAndFetch(-1)
        if (remaining == 0 && isClosed.load()) {
            exitNotifier.complete(Unit)
        }
    }

    internal fun spawn(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {
        val tok = token()
        return scope.launch {
            try {
                block()
            } finally {
                tok.close()
            }
        }
    }

    suspend fun wait() {
        if (isClosed.load() && taskCount.load() == 0) {
            return
        }
        exitNotifier.await()
    }

    companion object {
        fun new(): TaskTracker = TaskTracker()
    }
}

/**
 * Represents a task tracked by a [TaskTracker].
 */
class TaskTrackerToken(
    private val tracker: TaskTracker,
) : AutoCloseable {
    private val dropped = AtomicBoolean(false)

    override fun close() {
        if (dropped.compareAndSet(expectedValue = false, newValue = true)) {
            tracker.dropTask()
        }
    }
}
