// port-lint: tests codecs.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.Bytes
import io.github.kotlinmania.tokioutil.bytes.BytesMut
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CodecTest {
    @Test
    fun testLinesCodec() {
        val codec = LinesCodec.new()
        val buf = BytesMut.new()

        codec.encode("hello", buf)
        codec.encode("world", buf)

        val decoded1 = codec.decode(buf)
        assertEquals("hello", decoded1)

        val decoded2 = codec.decode(buf)
        assertEquals("world", decoded2)

        val decoded3 = codec.decode(buf)
        assertNull(decoded3)
    }

    @Test
    fun testBytesCodec() {
        val codec = BytesCodec.new()
        val buf = BytesMut.new()
        val data = byteArrayOf(1, 2, 3, 4)

        codec.encode(data, buf)
        assertEquals(4, buf.len())

        val decoded = codec.decode(buf)
        assertEquals(4, decoded?.len())
        assertContentEquals(byteArrayOf(1, 2, 3, 4), decoded?.toByteArray())
    }

    @Test
    fun testAnyDelimiterCodec() {
        val codec = AnyDelimiterCodec.new(byteArrayOf(','.code.toByte()), byteArrayOf(','.code.toByte()))
        val buf = BytesMut.new()
        buf.put("alpha,beta,gamma".encodeToByteArray())

        val item1 = codec.decode(buf)
        assertEquals("alpha", item1?.toByteArray()?.decodeToString())

        val item2 = codec.decode(buf)
        assertEquals("beta", item2?.toByteArray()?.decodeToString())

        val item3 = codec.decodeEof(buf)
        assertEquals("gamma", item3?.toByteArray()?.decodeToString())
    }

    @Test
    fun testLengthDelimitedCodec() {
        val codec = LengthDelimitedCodec.builder()
            .lengthFieldLength(4)
            .newCodec()

        val buf = BytesMut.new()
        val payload = byteArrayOf(10, 20, 30)

        codec.encode(payload, buf)
        assertEquals(7, buf.len()) // 4 bytes header + 3 bytes payload

        val decoded = codec.decode(buf)
        assertEquals(3, decoded?.len())
        assertContentEquals(byteArrayOf(10, 20, 30), decoded?.toByteArray())
    }
}
