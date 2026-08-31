// port-lint: source tokio-util/src/codec/framed_impl.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

internal class ReadFrame(
    var eof: Boolean = false,
    var isReadable: Boolean = false,
    var buffer: BytesMut = BytesMut.withCapacity(INITIAL_CAPACITY),
    var hasErrored: Boolean = false,
) {
    companion object {
        const val INITIAL_CAPACITY: Int = 8 * 1024
    }
}

internal class WriteFrame(
    var buffer: BytesMut = BytesMut.withCapacity(INITIAL_CAPACITY),
    var backpressureBoundary: Int = INITIAL_CAPACITY,
) {
    companion object {
        const val INITIAL_CAPACITY: Int = 8 * 1024
    }
}

internal class RWFrames(
    val read: ReadFrame = ReadFrame(),
    val write: WriteFrame = WriteFrame(),
)

internal class FramedImpl<T, U, State>(
    var inner: T,
    var codec: U,
    var state: State,
)
