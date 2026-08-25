// port-lint: source sync/reusable_box.rs
package io.github.kotlinmania.tokioutil.sync

/**
 * A reusable container for an asynchronous task or computation.
 */
internal class ReusableBoxFuture<T>(
    private var future: suspend () -> T,
) {
    /**
     * Replaces the future currently stored in this box.
     */
    fun set(newFuture: suspend () -> T) {
        this.future = newFuture
    }

    /**
     * Executes the stored future.
     */
    suspend fun get(): T = future()

    companion object {
        fun <T> new(future: suspend () -> T): ReusableBoxFuture<T> =
            ReusableBoxFuture(future)
    }
}
