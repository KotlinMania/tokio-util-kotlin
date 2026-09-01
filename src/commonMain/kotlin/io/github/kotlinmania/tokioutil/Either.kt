// port-lint: source either.rs
package io.github.kotlinmania.tokioutil

/**
 * Combines two different types into a single type.
 */
sealed class Either<out L, out R> {
    data class Left<out L>(
        val value: L,
    ) : Either<L, Nothing>()

    data class Right<out R>(
        val value: R,
    ) : Either<Nothing, R>()

    val isLeft: Boolean get() = this is Left
    val isRight: Boolean get() = this is Right

    fun leftOrNull(): L? = (this as? Left)?.value

    fun rightOrNull(): R? = (this as? Right)?.value

    inline fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T =
        when (this) {
            is Left -> onLeft(value)
            is Right -> onRight(value)
        }
}
