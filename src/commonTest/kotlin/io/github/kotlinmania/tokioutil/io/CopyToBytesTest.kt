// port-lint: source io/copy_to_bytes.rs
package io.github.kotlinmania.tokioutil.io

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class CopyToBytesTest {
    @Test
    fun startSendCopiesSliceIntoOwnedBytes() {
        val sink = RecordingSink()
        val copyToBytes = CopyToBytes.new(sink)
        val bytes = byteArrayOf(1, 2, 3)

        val result = copyToBytes.startSend(bytes)
        bytes[0] = 9

        assertTrue(result.isSuccess)
        assertContentEquals(byteArrayOf(1, 2, 3), sink.sent.single())
    }

    @Test
    fun accessorsExposeTheUnderlyingSink() {
        val sink = RecordingSink()
        val copyToBytes = CopyToBytes.new(sink)

        assertSame(sink, copyToBytes.getRef())
        assertSame(sink, copyToBytes.getMut())
        assertSame(sink, copyToBytes.intoInner())
    }

    @Test
    fun pollNextDelegatesToUnderlyingStream() {
        val sink = RecordingStreamSink(mutableListOf("next"))
        val copyToBytes = CopyToBytes.new(sink)

        assertEquals("next", copyToBytes.pollNext().getOrThrow())
        assertNull(copyToBytes.pollNext().getOrThrow())
    }

    private class RecordingSink : ByteSink {
        val sent = mutableListOf<ByteArray>()

        override fun pollReady(): Result<Unit> = Result.success(Unit)

        override fun startSend(item: ByteArray): Result<Unit> {
            sent += item
            return Result.success(Unit)
        }

        override fun pollFlush(): Result<Unit> = Result.success(Unit)

        override fun pollClose(): Result<Unit> = Result.success(Unit)
    }

    private class RecordingStreamSink(
        private val items: MutableList<Any>,
    ) : ByteSink, ByteStream {
        override fun pollReady(): Result<Unit> = Result.success(Unit)

        override fun startSend(item: ByteArray): Result<Unit> = Result.success(Unit)

        override fun pollFlush(): Result<Unit> = Result.success(Unit)

        override fun pollClose(): Result<Unit> = Result.success(Unit)

        override fun pollNext(): Result<Any?> =
            Result.success(if (items.isEmpty()) null else items.removeAt(0))
    }
}
