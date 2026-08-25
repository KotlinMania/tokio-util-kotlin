// port-lint: source io/inspect.rs
package io.github.kotlinmania.tokioutil.io

/**
 * An adapter that lets you inspect data that's being read.
 */
internal class InspectReader<R>(
    val reader: R,
    private val callback: (ByteArray, Int, Int) -> Unit,
) {
    fun inspect(bytes: ByteArray, offset: Int, length: Int) {
        callback(bytes, offset, length)
    }

    companion object {
        fun <R> new(reader: R, callback: (ByteArray, Int, Int) -> Unit): InspectReader<R> =
            InspectReader(reader, callback)
    }
}

/**
 * An adapter that lets you inspect data that's being written.
 */
internal class InspectWriter<W>(
    val writer: W,
    private val callback: (ByteArray, Int, Int) -> Unit,
) {
    fun inspect(bytes: ByteArray, offset: Int, length: Int) {
        callback(bytes, offset, length)
    }

    companion object {
        fun <W> new(writer: W, callback: (ByteArray, Int, Int) -> Unit): InspectWriter<W> =
            InspectWriter(writer, callback)
    }
}
