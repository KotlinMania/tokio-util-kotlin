// port-lint: source tokio-util/src/net/unix/mod.rs
package io.github.kotlinmania.tokioutil.net.unix

import io.github.kotlinmania.tokioutil.net.Listener

/**
 * Unix domain socket listener interface.
 */
interface UnixListener<Io, Addr> : Listener<Io, Addr>
