// port-lint: source tokio-util/src/io/read_buf.rs
package io.github.kotlinmania.tokioutil.io

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * Reads data from an asynchronous source into a [BytesMut] buffer.
 */
suspend fun readBuf(
    readSource: suspend (ByteArray, Int, Int) -> Int,
    buf: BytesMut,
): Int {
    val temp = ByteArray(4096)
    val n = readSource(temp, 0, temp.size)
    if (n > 0) {
        buf.put(temp, 0, n)
    }
    return n
}
