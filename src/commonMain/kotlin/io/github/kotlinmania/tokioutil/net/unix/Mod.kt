// port-lint: source net/unix/mod.rs
package io.github.kotlinmania.tokioutil.net.unix

import io.github.kotlinmania.tokioutil.net.Listener

/**
 * Unix domain socket listener interface.
 */
interface UnixListener<Io, Addr> : Listener<Io, Addr>
