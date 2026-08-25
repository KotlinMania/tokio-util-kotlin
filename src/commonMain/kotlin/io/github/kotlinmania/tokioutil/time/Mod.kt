// port-lint: source time/mod.rs
package io.github.kotlinmania.tokioutil.time

/**
 * Module ledger for time utilities.
 */
internal object TimeModLedger {
    val delayQueueClass = DelayQueue::class
    val keyClass = Key::class
    val expiredClass = Expired::class
}
