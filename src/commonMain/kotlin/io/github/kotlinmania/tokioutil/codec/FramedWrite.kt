// port-lint: source codec/framed_write.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A sink of frames encoded to an underlying I/O writer.
 */
internal class FramedWrite<T, E : Encoder<*>>(
    private val inner: FramedImpl<T, E, WriteFrame>,
) {
    constructor(inner: T, encoder: E) : this(
        FramedImpl(inner, encoder, WriteFrame())
    )

    constructor(inner: T, encoder: E, capacity: Int) : this(
        FramedImpl(inner, encoder, WriteFrame(buffer = BytesMut.withCapacity(capacity), backpressureBoundary = capacity))
    )

    fun getRef(): T = inner.inner

    fun getMut(): T = inner.inner

    fun intoInner(): T = inner.inner

    fun encoder(): E = inner.codec

    fun writeBuffer(): BytesMut = inner.state.buffer

    fun writeBufferMut(): BytesMut = inner.state.buffer

    fun backpressureBoundary(): Int = inner.state.backpressureBoundary

    fun setBackpressureBoundary(boundary: Int) {
        inner.state.backpressureBoundary = boundary
    }

    companion object {
        fun <T, E : Encoder<*>> new(inner: T, encoder: E): FramedWrite<T, E> =
            FramedWrite(inner, encoder)

        fun <T, E : Encoder<*>> withCapacity(inner: T, encoder: E, capacity: Int): FramedWrite<T, E> =
            FramedWrite(inner, encoder, capacity)
    }
}
