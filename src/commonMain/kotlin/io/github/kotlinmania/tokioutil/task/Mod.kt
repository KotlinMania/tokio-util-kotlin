// port-lint: source tokio-util/src/task/mod.rs
package io.github.kotlinmania.tokioutil.task

/**
 * Module ledger for task utilities.
 */
internal object TaskModLedger {
    val abortOnDropHandleClass = AbortOnDropHandle::class
    val taskTrackerClass = TaskTracker::class
    val joinMapClass = JoinMap::class
    val joinQueueClass = JoinQueue::class
    val localPoolHandleClass = LocalPoolHandle::class
}
