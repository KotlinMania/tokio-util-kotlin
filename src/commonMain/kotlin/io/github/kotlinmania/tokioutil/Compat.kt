// port-lint: source compat.rs
package io.github.kotlinmania.tokioutil

/**
 * Compatibility wrapper between different async stream abstractions.
 */
internal class Compat<T>(
    private val inner: T,
) {
    fun getRef(): T = inner
    fun intoInner(): T = inner

    companion object {
        fun <T> new(inner: T): Compat<T> = Compat(inner)
    }
}
