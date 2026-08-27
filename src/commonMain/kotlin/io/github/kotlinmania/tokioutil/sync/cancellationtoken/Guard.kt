// port-lint: source tokio-util/src/sync/cancellation_token/guard.rs
package io.github.kotlinmania.tokioutil.sync.cancellationtoken

/**
 * A wrapper for cancellation token which automatically cancels it when closed.
 */
class DropGuard(
    private var inner: CancellationToken?,
) : AutoCloseable {
    /**
     * Returns stored cancellation token and disarms this drop guard instance.
     */
    fun disarm(): CancellationToken =
        inner?.also { inner = null } ?: error("inner was already disarmed or closed")

    override fun close() {
        inner?.cancel()
        inner = null
    }

    companion object {
        fun new(token: CancellationToken): DropGuard = DropGuard(token)
    }
}
