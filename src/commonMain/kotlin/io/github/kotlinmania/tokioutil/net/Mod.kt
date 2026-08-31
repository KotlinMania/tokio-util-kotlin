// port-lint: source tokio-util/src/net/mod.rs
package io.github.kotlinmania.tokioutil.net

import io.github.kotlinmania.tokioutil.Either

/**
 * A trait for a listener: `TcpListener` and `UnixListener`.
 */
interface Listener<Io, Addr> {
    /**
     * Accepts a new incoming connection from this listener.
     */
    suspend fun accept(): Pair<Io, Addr>

    /**
     * Returns the local address that this listener is bound to.
     */
    fun localAddr(): Addr
}

/**
 * Accepts a connection from an [Either] pair of listeners.
 */
suspend fun <L_Io, L_Addr, R_Io, R_Addr> Either<Listener<L_Io, L_Addr>, Listener<R_Io, R_Addr>>.accept(): Either<Pair<L_Io, L_Addr>, Pair<R_Io, R_Addr>> =
    when (this) {
        is Either.Left -> Either.Left(value.accept())
        is Either.Right -> Either.Right(value.accept())
    }

/**
 * Returns the local address from an [Either] pair of listeners.
 */
fun <L_Io, L_Addr, R_Io, R_Addr> Either<Listener<L_Io, L_Addr>, Listener<R_Io, R_Addr>>.localAddr(): Either<L_Addr, R_Addr> =
    when (this) {
        is Either.Left -> Either.Left(value.localAddr())
        is Either.Right -> Either.Right(value.localAddr())
    }
