// port-lint: source codec/bytes_codec.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.bytes.Bytes
import io.github.kotlinmania.bytes.BytesMut

/**
 * A simple [Decoder] and [Encoder] implementation that ships raw bytes around.
 */
class BytesCodec :
    Decoder<BytesMut>,
    Encoder<ByteArray> {
    override fun decode(src: BytesMut): BytesMut? =
        if (!src.isEmpty()) {
            src.splitTo(src.len())
        } else {
            null
        }

    override fun encode(item: ByteArray, dst: BytesMut) {
        dst.extendFromSlice(item)
    }

    fun encodeBytes(data: Bytes, buf: BytesMut) {
        buf.extendFromSlice(data.asSlice())
    }

    fun encodeBytesMut(data: BytesMut, buf: BytesMut) {
        buf.put(data)
    }

    companion object {
        fun new(): BytesCodec = BytesCodec()
    }
}
