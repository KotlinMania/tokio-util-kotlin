// port-lint: source io/sync_bridge.rs
package io.github.kotlinmania.tokioutil.io

/**
 * Bridge for synchronous I/O operations from asynchronous sources.
 */
internal class SyncIoBridge<T>(
    private val src: T,
) {
    fun intoInner(): T = src

    fun getRef(): T = src

    companion object {
        fun <T> new(src: T): SyncIoBridge<T> = SyncIoBridge(src)
    }
}
