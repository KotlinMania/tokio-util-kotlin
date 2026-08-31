// port-lint: tests tokio-util/tests/sync_cancellation_token.rs
package io.github.kotlinmania.tokioutil.sync.cancellationtoken

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CancellationTokenTest {
    @Test
    fun testCancellationBasic() {
        val token = CancellationToken.new()
        assertFalse(token.isCancelled())

        token.cancel()
        assertTrue(token.isCancelled())
    }

    @Test
    fun testChildCancellation() {
        val parent = CancellationToken.new()
        val child = parent.childToken()

        assertFalse(parent.isCancelled())
        assertFalse(child.isCancelled())

        parent.cancel()
        assertTrue(parent.isCancelled())
        assertTrue(child.isCancelled())
    }

    @Test
    fun testChildIndependentCancellation() {
        val parent = CancellationToken.new()
        val child = parent.childToken()

        child.cancel()
        assertTrue(child.isCancelled())
        assertFalse(parent.isCancelled())
    }

    @Test
    fun testDropGuard() {
        val token = CancellationToken.new()
        val guard = token.dropGuard()
        assertFalse(token.isCancelled())

        guard.close()
        assertTrue(token.isCancelled())
    }
}
