// port-lint: source io/copy_to_bytes.rs
package io.github.kotlinmania.tokioutil.io

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class CopyToBytesTest {
    @Test
    fun accessorsExposeTheUnderlyingSink() {
        val sink = "test-sink"
        val copyToBytes = CopyToBytes.new(sink)

        assertSame(sink, copyToBytes.getRef())
        assertSame(sink, copyToBytes.getMut())
        assertSame(sink, copyToBytes.intoInner())
    }
}
