// port-lint: source tokio-util/src/sync/poll_semaphore.rs
package io.github.kotlinmania.tokioutil.sync

import kotlinx.coroutines.sync.Semaphore

/**
 * A wrapper around [Semaphore] that provides permit acquisition utilities.
 */
internal class PollSemaphore(
    val permits: Int,
) {
    private val semaphore = Semaphore(permits)
    private var isClosed = false

    fun availablePermits(): Int = semaphore.availablePermits

    fun isClosed(): Boolean = isClosed

    fun close() {
        isClosed = true
    }

    suspend fun acquire(): Boolean {
        if (isClosed) return false
        semaphore.acquire()
        return true
    }

    fun tryAcquire(): Boolean {
        if (isClosed) return false
        return semaphore.tryAcquire()
    }

    fun release() {
        semaphore.release()
    }

    suspend fun <T> withPermit(action: suspend () -> T): T? {
        if (isClosed) return null
        semaphore.acquire()
        return try {
            action()
        } finally {
            semaphore.release()
        }
    }

    companion object {
        fun new(permits: Int): PollSemaphore = PollSemaphore(permits)
    }
}
