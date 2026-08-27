// port-lint: source tokio-util/src/future/with_cancellation_token.rs
package io.github.kotlinmania.tokioutil.future

import io.github.kotlinmania.tokioutil.sync.cancellationtoken.CancellationToken

/**
 * A future / asynchronous computation that is resolved once the corresponding [CancellationToken]
 * is cancelled or a given computation gets resolved.
 */
internal class WithCancellationTokenFuture<T>(
    private val cancellationToken: CancellationToken,
    private val block: suspend () -> T,
) {
    suspend fun await(): T? {
        if (cancellationToken.isCancelled()) return null
        return cancellationToken.runUntilCancelled(block)
    }

    internal companion object {
        fun <T> new(cancellationToken: CancellationToken, block: suspend () -> T): WithCancellationTokenFuture<T> =
            WithCancellationTokenFuture(cancellationToken, block)
    }
}

/**
 * An owned variant of [WithCancellationTokenFuture].
 */
internal class WithCancellationTokenFutureOwned<T>(
    private val cancellationToken: CancellationToken,
    private val block: suspend () -> T,
) {
    suspend fun await(): T? {
        if (cancellationToken.isCancelled()) return null
        return cancellationToken.runUntilCancelledOwned(block)
    }

    internal companion object {
        fun <T> new(cancellationToken: CancellationToken, block: suspend () -> T): WithCancellationTokenFutureOwned<T> =
            WithCancellationTokenFutureOwned(cancellationToken, block)
    }
}
