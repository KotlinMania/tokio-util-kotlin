// port-lint: source tokio-util/src/codec/bytes_codec.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.Bytes
import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A simple [Decoder] and [Encoder] implementation that ships raw bytes around.
 */
class BytesCodec :
    Decoder<BytesMut>,
    Encoder<ByteArray> {
    override fun decode(src: BytesMut): BytesMut? =
        if (src.isNotEmpty()) {
            src.splitTo(src.len())
        } else {
            null
        }

    override fun encode(item: ByteArray, dst: BytesMut) {
        dst.put(item)
    }

    fun encodeBytes(data: Bytes, buf: BytesMut) {
        buf.put(data)
    }

    fun encodeBytesMut(data: BytesMut, buf: BytesMut) {
        buf.put(data)
    }

    companion object {
        fun new(): BytesCodec = BytesCodec()
    }
}
