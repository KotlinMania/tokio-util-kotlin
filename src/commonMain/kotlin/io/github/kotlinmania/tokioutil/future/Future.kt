// port-lint: source tokio-util/src/future.rs
package io.github.kotlinmania.tokioutil.future

import io.github.kotlinmania.tokioutil.sync.cancellationtoken.CancellationToken
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration

/**
 * Extension functions that provide convenient adapters for asynchronous blocks.
 */
internal object FutureExt {
    suspend fun <T> timeout(
        duration: Duration,
        block: suspend () -> T,
    ): T = withTimeout(duration) { block() }

    suspend fun <T> timeoutOrNull(
        duration: Duration,
        block: suspend () -> T,
    ): T? = withTimeoutOrNull(duration) { block() }

    fun <T> withCancellationToken(
        cancellationToken: CancellationToken,
        block: suspend () -> T,
    ): WithCancellationTokenFuture<T> =
        WithCancellationTokenFuture.new(cancellationToken, block)

    fun <T> withCancellationTokenOwned(
        cancellationToken: CancellationToken,
        block: suspend () -> T,
    ): WithCancellationTokenFutureOwned<T> =
        WithCancellationTokenFutureOwned.new(cancellationToken, block)
}

/**
 * Module ledger for future utilities.
 */
internal object FutureModLedger {
    val futureExt = FutureExt
    val withCancellationTokenFutureClass = WithCancellationTokenFuture::class
    val withCancellationTokenFutureOwnedClass = WithCancellationTokenFutureOwned::class
}
