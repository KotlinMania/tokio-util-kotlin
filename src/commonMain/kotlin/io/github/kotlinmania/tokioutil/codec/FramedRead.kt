// port-lint: source tokio-util/src/codec/framed_read.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A stream of messages decoded from an underlying I/O object.
 */
internal class FramedRead<T, D : Decoder<*>>(
    private val inner: FramedImpl<T, D, ReadFrame>,
) {
    constructor(inner: T, decoder: D) : this(
        FramedImpl(inner, decoder, ReadFrame()),
    )

    constructor(inner: T, decoder: D, capacity: Int) : this(
        FramedImpl(inner, decoder, ReadFrame(buffer = BytesMut.withCapacity(capacity))),
    )

    fun getRef(): T = inner.inner

    fun getMut(): T = inner.inner

    fun intoInner(): T = inner.inner

    fun decoder(): D = inner.codec

    fun readBuffer(): BytesMut = inner.state.buffer

    fun readBufferMut(): BytesMut = inner.state.buffer

    companion object {
        fun <T, D : Decoder<*>> new(inner: T, decoder: D): FramedRead<T, D> =
            FramedRead(inner, decoder)

        fun <T, D : Decoder<*>> withCapacity(inner: T, decoder: D, capacity: Int): FramedRead<T, D> =
            FramedRead(inner, decoder, capacity)
    }
}
