// port-lint: source io/copy_to_bytes.rs
package io.github.kotlinmania.tokioutil.io

/**
 * A helper that wraps a [ByteSink] and converts it into a sink of [ByteArray]
 * by copying each byte slice into an owned [ByteArray].
 *
 * This is the byte-copying helper used by sink writers when they accept
 * borrowed byte slices.
 */
class CopyToBytes<S : ByteSink> private constructor(
    private var inner: S,
) : ByteSink {
    companion object {
        /** Creates a new [CopyToBytes]. */
        fun <S : ByteSink> new(inner: S): CopyToBytes<S> = CopyToBytes(inner)
    }

    /** Gets a reference to the underlying sink. */
    fun getRef(): S = inner

    /** Gets a mutable reference to the underlying sink. */
    fun getMut(): S = inner

    /** Consumes this [CopyToBytes], returning the underlying sink. */
    fun intoInner(): S = inner

    override fun pollReady(): Result<Unit> =
        inner.pollReady()

    override fun startSend(item: ByteArray): Result<Unit> =
        inner.startSend(item.copyOf())

    override fun pollFlush(): Result<Unit> =
        inner.pollFlush()

    override fun pollClose(): Result<Unit> =
        inner.pollClose()

    fun pollNext(): Result<Any?> =
        when (val stream = inner) {
            is ByteStream -> stream.pollNext()
            else -> Result.failure(IllegalStateException("underlying sink is not a stream"))
        }
}

interface ByteSink {
    fun pollReady(): Result<Unit>

    fun startSend(item: ByteArray): Result<Unit>

    fun pollFlush(): Result<Unit>

    fun pollClose(): Result<Unit>
}

interface ByteStream {
    fun pollNext(): Result<Any?>
}
