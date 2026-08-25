// port-lint: source io/reader_stream.rs
package io.github.kotlinmania.tokioutil.io

import io.github.kotlinmania.tokioutil.bytes.Bytes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Converts an asynchronous reader function into a [Flow] of [Bytes] chunks.
 */
internal class ReaderStream(
    private val readFn: suspend (ByteArray) -> Int,
    val capacity: Int = DEFAULT_CAPACITY,
) {
    internal fun toFlow(): Flow<Bytes> = flow {
        val buf = ByteArray(capacity)
        while (true) {
            val read = readFn(buf)
            if (read <= 0) break
            emit(Bytes.copyFromSlice(buf, 0, read))
        }
    }

    internal companion object {
        const val DEFAULT_CAPACITY: Int = 4096

        fun new(readFn: suspend (ByteArray) -> Int): ReaderStream =
            ReaderStream(readFn)

        fun withCapacity(readFn: suspend (ByteArray) -> Int, capacity: Int): ReaderStream =
            ReaderStream(readFn, capacity)
    }
}
