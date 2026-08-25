// port-lint: source future.rs
package io.github.kotlinmania.tokioutil.future

/**
 * Module ledger for future utilities.
 */
internal object FutureModLedger {
    val futureExt = FutureExt
    val withCancellationTokenFutureClass = WithCancellationTokenFuture::class
    val withCancellationTokenFutureOwnedClass = WithCancellationTokenFutureOwned::class
}
