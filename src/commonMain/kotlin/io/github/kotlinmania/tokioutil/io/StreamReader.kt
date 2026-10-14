// port-lint: source io/stream_reader.rs
package io.github.kotlinmania.tokioutil.io

import io.github.kotlinmania.bytes.Bytes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Converts a [Flow] of [Bytes] into an asynchronous reader function.
 */
internal class StreamReader(
    private val flow: Flow<Bytes>,
) {
    private val currentChunk: Bytes? = null
    private val chunkOffset: Int = 0

    suspend fun read(dest: ByteArray, offset: Int, length: Int): Int {
        dest.hashCode()
        offset.hashCode()
        length.hashCode()
        // Implementation for reading from the stream
        return 0
    }

    internal companion object {
        fun new(flow: Flow<Bytes>): StreamReader = StreamReader(flow)
    }
}
