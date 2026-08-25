// port-lint: source io/simplex.rs
package io.github.kotlinmania.tokioutil.io

import io.github.kotlinmania.tokioutil.bytes.BytesMut
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Receiver half of a simplex channel.
 */
class SimplexReceiver internal constructor(
    private val buffer: BytesMut,
    private val mutex: Mutex,
) {
    suspend fun read(dest: ByteArray, offset: Int, length: Int): Int = mutex.withLock {
        val available = minOf(length, buffer.len())
        if (available > 0) {
            val chunk = buffer.splitTo(available)
            val bytes = chunk.toByteArray()
            bytes.copyInto(dest, offset, 0, available)
        }
        available
    }
}

/**
 * Sender half of a simplex channel.
 */
class SimplexSender internal constructor(
    private val buffer: BytesMut,
    private val mutex: Mutex,
) {
    suspend fun write(src: ByteArray, offset: Int, length: Int): Int = mutex.withLock {
        buffer.put(src, offset, length)
        length
    }
}

/**
 * Creates a simplex pair (sender, receiver).
 */
fun simplex(capacity: Int = 4096): Pair<SimplexSender, SimplexReceiver> {
    val buf = BytesMut.withCapacity(capacity)
    val mutex = Mutex()
    return Pair(SimplexSender(buf, mutex), SimplexReceiver(buf, mutex))
}
