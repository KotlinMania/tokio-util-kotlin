// port-lint: source tokio-util/src/task/abort_on_drop.rs
package io.github.kotlinmania.tokioutil.task

import kotlinx.coroutines.Job

/**
 * A wrapper around a coroutine [Job] which aborts/cancels the task when closed/dropped.
 */
internal class AbortOnDropHandle(
    private val job: Job,
) : AutoCloseable {
    fun abort() {
        job.cancel()
    }

    fun isFinished(): Boolean = job.isCompleted

    override fun close() {
        abort()
    }

    companion object {
        fun new(job: Job): AbortOnDropHandle = AbortOnDropHandle(job)
    }
}
