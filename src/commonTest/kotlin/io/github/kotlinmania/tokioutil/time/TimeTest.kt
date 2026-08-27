// port-lint: tests tokio-util/tests/time_delay_queue.rs
package io.github.kotlinmania.tokioutil.time

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds

class TimeTest {
    @Test
    fun testDelayQueueBasic() {
        val queue = DelayQueue.new<String>()
        assertEquals(0, queue.len())
        assertTrue(queue.isEmpty())

        val key1 = queue.insert("item1", 10.milliseconds)
        assertEquals(1, queue.len())
        assertFalse(queue.isEmpty())

        val removed = queue.remove(key1)
        assertEquals("item1", removed)
        assertEquals(0, queue.len())
    }
}
