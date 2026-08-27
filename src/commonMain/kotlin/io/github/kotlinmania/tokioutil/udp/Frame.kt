// port-lint: source tokio-util/src/udp/frame.rs
package io.github.kotlinmania.tokioutil.udp

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * Socket address representation for UDP frames.
 */
data class SocketAddr(
    val ip: String,
    val port: Int,
)

/**
 * A unified stream and sink interface to an underlying UDP socket.
 */
internal class UdpFramed<C, T>(
    val socket: T,
    val codec: C,
    private val rd: BytesMut = BytesMut.withCapacity(INITIAL_RD_CAPACITY),
    private val wr: BytesMut = BytesMut.withCapacity(INITIAL_WR_CAPACITY),
) {
    fun readBuffer(): BytesMut = rd

    fun writeBuffer(): BytesMut = wr

    companion object {
        const val INITIAL_RD_CAPACITY: Int = 64 * 1024
        const val INITIAL_WR_CAPACITY: Int = 8 * 1024

        fun <C, T> new(socket: T, codec: C): UdpFramed<C, T> =
            UdpFramed(socket, codec)
    }
}
