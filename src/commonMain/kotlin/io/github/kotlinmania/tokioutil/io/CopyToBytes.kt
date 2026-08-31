// port-lint: source tokio-util/src/io/copy_to_bytes.rs
package io.github.kotlinmania.tokioutil.io

/**
 * A helper that wraps an underlying sink and copies byte chunks.
 */
internal class CopyToBytes<S>(
    private val inner: S,
) {
    fun getRef(): S = inner

    fun getMut(): S = inner

    fun intoInner(): S = inner

    companion object {
        fun <S> new(inner: S): CopyToBytes<S> = CopyToBytes(inner)
    }
}
