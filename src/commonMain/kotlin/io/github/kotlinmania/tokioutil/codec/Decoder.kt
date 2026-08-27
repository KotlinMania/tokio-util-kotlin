// port-lint: source tokio-util/src/codec/decoder.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * Decoding of frames via buffers.
 */
internal interface Decoder<T> {
    /**
     * Attempts to decode a frame from the provided buffer of bytes.
     */
    fun decode(src: BytesMut): T?

    /**
     * Called when there are no more bytes available to be read from the underlying I/O.
     */
    fun decodeEof(buf: BytesMut): T? {
        val frame = decode(buf)
        if (frame != null) {
            return frame
        }
        if (buf.isEmpty()) {
            return null
        }
        throw IllegalStateException("bytes remaining on stream at EOF (${buf.len()} bytes)")
    }
}
