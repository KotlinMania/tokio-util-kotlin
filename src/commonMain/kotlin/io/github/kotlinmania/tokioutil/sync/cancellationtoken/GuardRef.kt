// port-lint: source tokio-util/src/sync/cancellation_token/guard_ref.rs
package io.github.kotlinmania.tokioutil.sync.cancellationtoken

/**
 * A borrowed wrapper for cancellation token which automatically cancels it when closed.
 */
class DropGuardRef(
    private var inner: CancellationToken?,
) : AutoCloseable {
    /**
     * Disarms this guard so it will not cancel the token on close.
     */
    fun disarm(): CancellationToken =
        inner?.also { inner = null } ?: error("inner was already disarmed or closed")

    override fun close() {
        inner?.cancel()
        inner = null
    }

    companion object {
        fun new(token: CancellationToken): DropGuardRef = DropGuardRef(token)
    }
}
