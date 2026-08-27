// port-lint: source tokio-util/src/sync/mod.rs
package io.github.kotlinmania.tokioutil.sync

import io.github.kotlinmania.tokioutil.sync.cancellationtoken.CancellationToken

/**
 * Module ledger for synchronization primitives.
 */
internal object SyncModLedger {
    val cancellationTokenClass = CancellationToken::class
    val pollSenderClass = PollSender::class
    val pollSemaphoreClass = PollSemaphore::class
    val reusableBoxFutureClass = ReusableBoxFuture::class
}
