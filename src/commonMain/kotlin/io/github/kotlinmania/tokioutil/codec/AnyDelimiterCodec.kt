// port-lint: source codec/any_delimiter_codec.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.Bytes
import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A simple [Decoder] and [Encoder] implementation that splits up data into chunks based on any delimiter byte.
 */
class AnyDelimiterCodec(
    private val seekDelimiters: ByteArray = byteArrayOf(','.code.toByte(), ';'.code.toByte(), '\n'.code.toByte(), '\r'.code.toByte()),
    private val sequenceWriter: ByteArray = byteArrayOf(','.code.toByte()),
    private val maxLength: Int = Int.MAX_VALUE,
) : Decoder<Bytes>,
    Encoder<String> {
    private var nextIndex: Int = 0
    private var isDiscarding: Boolean = false

    fun maxLength(): Int = maxLength

    override fun decode(src: BytesMut): Bytes? {
        while (true) {
            val readTo =
                minOf(
                    if (maxLength == Int.MAX_VALUE) Int.MAX_VALUE else maxLength + 1,
                    src.len(),
                )

            var chunkOffset: Int? = null
            for (i in nextIndex until readTo) {
                if (seekDelimiters.contains(src[i])) {
                    chunkOffset = i - nextIndex
                    break
                }
            }

            if (isDiscarding) {
                if (chunkOffset != null) {
                    src.splitTo(chunkOffset + nextIndex + 1)
                    isDiscarding = false
                    nextIndex = 0
                } else {
                    src.splitTo(readTo)
                    nextIndex = 0
                    if (src.isEmpty()) {
                        return null
                    }
                }
            } else {
                if (chunkOffset != null) {
                    val chunkIndex = chunkOffset + nextIndex
                    nextIndex = 0
                    val chunk = src.splitTo(chunkIndex + 1)
                    chunk.truncate(chunk.len() - 1)
                    return chunk.freeze()
                } else if (src.len() > maxLength) {
                    isDiscarding = true
                    throw IllegalStateException("max chunk length exceeded")
                } else {
                    nextIndex = readTo
                    return null
                }
            }
        }
    }

    override fun decodeEof(buf: BytesMut): Bytes? {
        val decoded = decode(buf)
        if (decoded != null) {
            return decoded
        }
        if (buf.isEmpty()) {
            return null
        }
        val chunk = buf.splitTo(buf.len())
        nextIndex = 0
        return chunk.freeze()
    }

    override fun encode(item: String, dst: BytesMut) {
        val bytes = item.encodeToByteArray()
        dst.reserve(bytes.size + sequenceWriter.size)
        dst.put(bytes)
        dst.put(sequenceWriter)
    }

    companion object {
        fun new(seekDelimiters: ByteArray, sequenceWriter: ByteArray): AnyDelimiterCodec =
            AnyDelimiterCodec(seekDelimiters, sequenceWriter)

        fun newWithMaxLength(seekDelimiters: ByteArray, sequenceWriter: ByteArray, maxLength: Int): AnyDelimiterCodec =
            AnyDelimiterCodec(seekDelimiters, sequenceWriter, maxLength)
    }
}
