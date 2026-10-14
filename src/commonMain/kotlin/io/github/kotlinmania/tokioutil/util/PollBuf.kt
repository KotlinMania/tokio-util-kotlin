// port-lint: source util/poll_buf.rs
package io.github.kotlinmania.tokioutil.util

import io.github.kotlinmania.bytes.Bytes
import io.github.kotlinmania.bytes.BytesMut

/**
 * Try to read data from an asynchronous reader into a [BytesMut] buffer.
 */
fun pollReadBuf(
    readAction: (ByteArray, Int, Int) -> Int,
    buf: BytesMut,
    maxReadSize: Int = 8192,
): Int {
    val temp = ByteArray(maxReadSize)
    val bytesRead = readAction(temp, 0, temp.size)
    if (bytesRead > 0) {
        buf.extendFromSlice(temp.copyOfRange(0, bytesRead))
    }
    return bytesRead
}

/**
 * Try to write data from a [BytesMut] or [Bytes] buffer to an output stream.
 */
fun pollWriteBuf(
    writeAction: (ByteArray, Int, Int) -> Int,
    buf: BytesMut,
): Int {
    if (buf.isEmpty()) {
        return 0
    }
    val bytes = buf.deref()
    val written = writeAction(bytes, 0, bytes.size)
    if (written > 0) {
        buf.splitTo(written)
    }
    return written
}
