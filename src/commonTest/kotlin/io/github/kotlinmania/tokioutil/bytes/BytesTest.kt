// port-lint: tests tokio-util/src/bytes.rs
package io.github.kotlinmania.tokioutil.bytes

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class BytesTest {
    @Test
    fun testBytesBasic() {
        val b = Bytes.from(byteArrayOf(1, 2, 3, 4, 5))
        assertEquals(5, b.len())
        assertFalse(b.isEmpty())
        assertEquals(1.toByte(), b[0])
        assertEquals(5.toByte(), b[4])

        val slice = b.slice(1, 4)
        assertEquals(3, slice.len())
        assertContentEquals(byteArrayOf(2, 3, 4), slice.toByteArray())
    }

    @Test
    fun testBytesMutOperations() {
        val bm = BytesMut.withCapacity(10)
        bm.put(byteArrayOf(10, 20, 30))
        assertEquals(3, bm.len())

        val split = bm.splitTo(2)
        assertEquals(2, split.len())
        assertContentEquals(byteArrayOf(10, 20), split.toByteArray())
        assertEquals(1, bm.len())
        assertContentEquals(byteArrayOf(30), bm.toByteArray())

        bm.put(byteArrayOf(40, 50))
        val frozen = bm.freeze()
        assertEquals(3, frozen.len())
        assertContentEquals(byteArrayOf(30, 40, 50), frozen.toByteArray())
    }
}
