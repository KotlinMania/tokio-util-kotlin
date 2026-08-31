// port-lint: tests tokio-util/src/either.rs
package io.github.kotlinmania.tokioutil

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class EitherTest {
    @Test
    fun testEitherLeftAndRight() {
        val left: Either<String, Int> = Either.Left("hello")
        assertTrue(left.isLeft)
        assertFalse(left.isRight)
        assertEquals("hello", left.leftOrNull())
        assertNull(left.rightOrNull())
        assertEquals("LEFT: hello", left.fold({ "LEFT: $it" }, { "RIGHT: $it" }))

        val right: Either<String, Int> = Either.Right(42)
        assertFalse(right.isLeft)
        assertTrue(right.isRight)
        assertNull(right.leftOrNull())
        assertEquals(42, right.rightOrNull())
        assertEquals("RIGHT: 42", right.fold({ "LEFT: $it" }, { "RIGHT: $it" }))
    }
}
