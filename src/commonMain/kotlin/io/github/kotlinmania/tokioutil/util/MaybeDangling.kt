// port-lint: source util/maybe_dangling.rs
package io.github.kotlinmania.tokioutil.util

/**
 * A wrapper type that tells the compiler that the contents might not be valid.
 *
 * In Kotlin Multiplatform, this holds a value or reference and provides a container
 * for managed cleanup and scoped validity semantics matching upstream Rust.
 */
internal class MaybeDangling<T>(
    private var inner: T?,
) {
    /** Returns true if the value is present. */
    fun isPresent(): Boolean = inner != null

    /** Gets the inner value, or throws if cleared. */
    fun get(): T = inner ?: error("MaybeDangling accessed after release")

    /** Gets the inner value or null. */
    fun getOrNull(): T? = inner

    /** Releases the inner reference. */
    fun release(): T? {
        val current = inner
        inner = null
        return current
    }

    companion object {
        fun <T> new(inner: T): MaybeDangling<T> = MaybeDangling(inner)
    }
}
