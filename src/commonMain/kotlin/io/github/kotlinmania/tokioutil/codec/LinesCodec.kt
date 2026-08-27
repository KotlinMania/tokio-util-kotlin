// port-lint: source codec/lines_codec.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A simple [Decoder] and [Encoder] implementation that splits up data into lines.
 */
class LinesCodec(
    private val maxLength: Int = Int.MAX_VALUE,
) : Decoder<String>,
    Encoder<String> {
    private var nextIndex: Int = 0
    private var isDiscarding: Boolean = false

    fun maxLength(): Int = maxLength

    override fun decode(src: BytesMut): String? {
        while (true) {
            val readTo =
                minOf(
                    if (maxLength == Int.MAX_VALUE) Int.MAX_VALUE else maxLength + 1,
                    src.len(),
                )

            var newlineOffset: Int? = null
            for (i in nextIndex until readTo) {
                if (src[i] == '\n'.code.toByte()) {
                    newlineOffset = i - nextIndex
                    break
                }
            }

            if (isDiscarding) {
                if (newlineOffset != null) {
                    src.splitTo(newlineOffset + nextIndex + 1)
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
                if (newlineOffset != null) {
                    val newlineIndex = newlineOffset + nextIndex
                    nextIndex = 0
                    val lineBytes = src.splitTo(newlineIndex + 1).toByteArray()
                    var end = lineBytes.size - 1 // skip '\n'
                    if (end > 0 && lineBytes[end - 1] == '\r'.code.toByte()) {
                        end -= 1
                    }
                    return lineBytes.decodeToString(0, end)
                } else if (src.len() > maxLength) {
                    isDiscarding = true
                    throw IllegalStateException("max line length exceeded")
                } else {
                    nextIndex = readTo
                    return null
                }
            }
        }
    }

    override fun decodeEof(buf: BytesMut): String? {
        val decoded = decode(buf)
        if (decoded != null) {
            return decoded
        }
        nextIndex = 0
        if (buf.isEmpty()) {
            return null
        }
        val bytes = buf.splitTo(buf.len()).toByteArray()
        var end = bytes.size
        if (end > 0 && bytes[end - 1] == '\r'.code.toByte()) {
            end -= 1
        }
        if (end == 0) {
            return null
        }
        return bytes.decodeToString(0, end)
    }

    override fun encode(item: String, dst: BytesMut) {
        val bytes = item.encodeToByteArray()
        dst.reserve(bytes.size + 1)
        dst.put(bytes)
        dst.put('\n'.code.toByte())
    }

    companion object {
        fun new(): LinesCodec = LinesCodec()

        fun newWithMaxLength(maxLength: Int): LinesCodec = LinesCodec(maxLength)
    }
}
