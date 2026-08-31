// port-lint: tests tokio-util/src/task/task_tracker.rs
package io.github.kotlinmania.tokioutil.task

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TaskTest {
    @Test
    fun testTaskTracker() {
        val tracker = TaskTracker.new()
        assertEquals(0, tracker.len())
        assertTrue(tracker.isEmpty())
        assertFalse(tracker.isClosed())

        val token1 = tracker.token()
        assertEquals(1, tracker.len())

        val token2 = tracker.token()
        assertEquals(2, tracker.len())

        token1.close()
        assertEquals(1, tracker.len())

        token2.close()
        assertEquals(0, tracker.len())

        tracker.close()
        assertTrue(tracker.isClosed())
    }

    @Test
    fun testJoinMap() {
        val map = JoinMap.new<String, Int>()
        assertEquals(0, map.len())
        assertTrue(map.isEmpty())
    }

    @Test
    fun testJoinQueue() {
        val queue = JoinQueue.new<Int>()
        assertEquals(0, queue.len())
        assertTrue(queue.isEmpty())
    }
}
