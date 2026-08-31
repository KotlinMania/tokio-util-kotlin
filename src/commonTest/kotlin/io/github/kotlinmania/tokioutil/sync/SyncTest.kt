// port-lint: tests tokio-util/src/sync/mpsc.rs
package io.github.kotlinmania.tokioutil.sync

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SyncTest {
    @Test
    fun testPollSemaphore() {
        val sem = PollSemaphore.new(2)
        assertEquals(2, sem.availablePermits())

        assertTrue(sem.tryAcquire())
        assertEquals(1, sem.availablePermits())

        assertTrue(sem.tryAcquire())
        assertEquals(0, sem.availablePermits())

        assertFalse(sem.tryAcquire())

        sem.release()
        assertEquals(1, sem.availablePermits())
    }

    @Test
    fun testPollSender() {
        val sender = PollSender.new<Int>(10)
        assertFalse(sender.isClosed())

        val res = sender.trySend(42)
        assertTrue(res.isSuccess)

        sender.close()
        assertTrue(sender.isClosed())
    }
}
