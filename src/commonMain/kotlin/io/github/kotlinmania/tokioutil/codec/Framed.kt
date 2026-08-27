// port-lint: source tokio-util/src/codec/framed.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A unified interface to an underlying I/O object, using [Encoder] and [Decoder]
 * to encode and decode frames.
 */
internal class Framed<T, U>(
    private val inner: FramedImpl<T, U, RWFrames>,
) {
    constructor(inner: T, codec: U) : this(
        FramedImpl(inner, codec, RWFrames()),
    )

    constructor(inner: T, codec: U, capacity: Int) : this(
        FramedImpl(
            inner,
            codec,
            RWFrames(
                read = ReadFrame(buffer = BytesMut.withCapacity(capacity)),
                write = WriteFrame(buffer = BytesMut.withCapacity(capacity), backpressureBoundary = capacity),
            ),
        ),
    )

    fun getRef(): T = inner.inner

    fun getMut(): T = inner.inner

    fun intoInner(): T = inner.inner

    fun codec(): U = inner.codec

    fun readBuffer(): BytesMut = inner.state.read.buffer

    fun readBufferMut(): BytesMut = inner.state.read.buffer

    fun writeBuffer(): BytesMut = inner.state.write.buffer

    fun writeBufferMut(): BytesMut = inner.state.write.buffer

    fun backpressureBoundary(): Int = inner.state.write.backpressureBoundary

    fun setBackpressureBoundary(boundary: Int) {
        inner.state.write.backpressureBoundary = boundary
    }

    companion object {
        fun <T, U> new(inner: T, codec: U): Framed<T, U> = Framed(inner, codec)

        fun <T, U> withCapacity(inner: T, codec: U, capacity: Int): Framed<T, U> =
            Framed(inner, codec, capacity)
    }
}
