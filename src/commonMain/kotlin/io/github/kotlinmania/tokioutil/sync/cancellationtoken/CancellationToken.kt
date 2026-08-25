// port-lint: source sync/cancellation_token.rs
package io.github.kotlinmania.tokioutil.sync.cancellationtoken

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

/**
 * A token which can be used to signal a cancellation request to one or more tasks.
 */
class CancellationToken internal constructor(
    internal val inner: TreeNode,
) {
    /**
     * Creates a new [CancellationToken] in the non-cancelled state.
     */
    constructor() : this(TreeNode.new())

    /**
     * Creates a clone of the [CancellationToken] sharing the same cancellation state.
     */
    fun clone(): CancellationToken = CancellationToken(inner)

    /**
     * Creates a child [CancellationToken] which gets cancelled whenever the current token gets cancelled.
     */
    fun childToken(): CancellationToken = CancellationToken(inner.createChildSync())

    /**
     * Cancel the [CancellationToken] and all child tokens derived from it.
     */
    fun cancel() {
        inner.cancelSync()
    }

    /**
     * Returns true if the [CancellationToken] is cancelled.
     */
    fun isCancelled(): Boolean = inner.isCancelledSync()

    /**
     * Suspends until cancellation is requested.
     */
    suspend fun cancelled() {
        inner.awaitCancellation()
    }

    /**
     * Consumes this token and returns a future / suspension that resolves on cancellation.
     */
    suspend fun cancelledOwned() {
        inner.awaitCancellation()
    }

    /**
     * Creates a [DropGuard] for this token.
     */
    fun dropGuard(): DropGuard = DropGuard.new(this)

    /**
     * Creates a [DropGuardRef] for this token.
     */
    fun dropGuardRef(): DropGuardRef = DropGuardRef.new(this)

    /**
     * Runs an asynchronous block to completion and returns its result,
     * or returns null if cancelled.
     */
    internal suspend fun <T> runUntilCancelled(block: suspend () -> T): T? {
        if (isCancelled()) {
            return null
        }
        val completer = CompletableDeferred<T?>()
        inner.internalDeferred.invokeOnCompletion {
            completer.complete(null)
        }
        return kotlinx.coroutines.coroutineScope {
            val job = launch {
                try {
                    val res = block()
                    completer.complete(res)
                } catch (e: Throwable) {
                    completer.completeExceptionally(e)
                }
            }
            try {
                completer.await()
            } finally {
                job.cancel()
            }
        }
    }

    internal suspend fun <T> runUntilCancelledOwned(block: suspend () -> T): T? =
        runUntilCancelled(block)

    override fun toString(): String =
        "CancellationToken(isCancelled=${isCancelled()})"

    companion object {
        fun new(): CancellationToken = CancellationToken()
    }
}

