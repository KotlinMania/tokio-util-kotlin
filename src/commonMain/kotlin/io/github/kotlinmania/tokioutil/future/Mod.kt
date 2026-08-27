// port-lint: source tokio-util/tests/future.rs
package io.github.kotlinmania.tokioutil.future

/**
 * Module ledger for future utilities.
 */
internal object FutureModLedger {
    val futureExt = FutureExt
    val withCancellationTokenFutureClass = WithCancellationTokenFuture::class
    val withCancellationTokenFutureOwnedClass = WithCancellationTokenFutureOwned::class
}
