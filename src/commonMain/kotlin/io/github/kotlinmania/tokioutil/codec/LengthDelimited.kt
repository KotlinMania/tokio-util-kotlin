// port-lint: source codec/length_delimited.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.Bytes
import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * A codec for frames delimited by a frame head specifying their lengths.
 */
class LengthDelimitedCodec(
    private var builder: Builder = Builder.new(),
) : Decoder<BytesMut>,
    Encoder<ByteArray> {
    private sealed class DecodeState {
        object Head : DecodeState()

        class Data(
            val len: Int,
        ) : DecodeState()
    }

    private var state: DecodeState = DecodeState.Head

    fun maxFrameLength(): Int = builder.maxFrameLen

    fun setMaxFrameLength(valLen: Int) {
        builder.maxFrameLength(valLen)
    }

    private fun decodeHead(src: BytesMut): Int? {
        val headLen = builder.numHeadBytes()
        val fieldLen = builder.lengthFieldLen

        if (src.len() < headLen) {
            return null
        }

        val offset = builder.lengthFieldOffset
        var n: Long = 0
        if (builder.lengthFieldIsBigEndian) {
            for (i in 0 until fieldLen) {
                n = (n shl 8) or (src[offset + i].toLong() and 0xFFL)
            }
        } else {
            for (i in fieldLen - 1 downTo 0) {
                n = (n shl 8) or (src[offset + i].toLong() and 0xFFL)
            }
        }

        if (n > builder.maxFrameLen.toLong()) {
            throw IllegalStateException("frame length $n exceeds maximum ${builder.maxFrameLen}")
        }

        var adjusted = n.toInt() + builder.lengthAdjustment
        if (adjusted < 0) {
            throw IllegalArgumentException("adjusted length $adjusted is negative")
        }

        src.splitTo(builder.getNumSkip())
        src.reserve(maxOf(0, adjusted - src.len()))
        return adjusted
    }

    override fun decode(src: BytesMut): BytesMut? {
        val n =
            when (val s = state) {
                is DecodeState.Head -> {
                    val head = decodeHead(src) ?: return null
                    state = DecodeState.Data(head)
                    head
                }
                is DecodeState.Data -> s.len
            }

        if (src.len() < n) {
            return null
        }

        val data = src.splitTo(n)
        state = DecodeState.Head
        src.reserve(maxOf(0, builder.numHeadBytes() - src.len()))
        return data
    }

    override fun encode(item: ByteArray, dst: BytesMut) {
        encodeBytes(Bytes.copyFromSlice(item), dst)
    }

    fun encodeBytes(data: Bytes, dst: BytesMut) {
        val n = data.len()
        if (n > builder.maxFrameLen) {
            throw IllegalArgumentException("frame length $n exceeds maximum ${builder.maxFrameLen}")
        }
        val adjusted = n - builder.lengthAdjustment
        require(adjusted >= 0) { "adjusted length $adjusted is negative" }

        val fieldLen = builder.lengthFieldLen
        dst.reserve(fieldLen + n)

        if (builder.lengthFieldIsBigEndian) {
            for (i in fieldLen - 1 downTo 0) {
                dst.put(((adjusted ushr (i * 8)) and 0xFF).toByte())
            }
        } else {
            for (i in 0 until fieldLen) {
                dst.put(((adjusted ushr (i * 8)) and 0xFF).toByte())
            }
        }

        dst.put(data)
    }

    class Builder(
        var maxFrameLen: Int = 8 * 1024 * 1024,
        var lengthFieldLen: Int = 4,
        var lengthFieldOffset: Int = 0,
        var lengthAdjustment: Int = 0,
        var numSkip: Int? = null,
        var lengthFieldIsBigEndian: Boolean = true,
    ) {
        fun maxFrameLength(valLen: Int): Builder = apply { maxFrameLen = valLen }

        fun lengthFieldLength(valLen: Int): Builder = apply { lengthFieldLen = valLen }

        fun lengthFieldOffset(valOffset: Int): Builder = apply { lengthFieldOffset = valOffset }

        fun lengthAdjustment(valAdj: Int): Builder = apply { lengthAdjustment = valAdj }

        fun numSkip(valSkip: Int): Builder = apply { numSkip = valSkip }

        fun bigEndian(): Builder = apply { lengthFieldIsBigEndian = true }

        fun littleEndian(): Builder = apply { lengthFieldIsBigEndian = false }

        fun numHeadBytes(): Int {
            val numSkipVal = numSkip
            return if (numSkipVal != null) {
                maxOf(numSkipVal, lengthFieldOffset + lengthFieldLen)
            } else {
                lengthFieldOffset + lengthFieldLen
            }
        }

        fun getNumSkip(): Int = numSkip ?: (lengthFieldOffset + lengthFieldLen)

        fun newCodec(): LengthDelimitedCodec = LengthDelimitedCodec(this)

        companion object {
            fun new(): Builder = Builder()
        }
    }

    companion object {
        fun new(): LengthDelimitedCodec = LengthDelimitedCodec()

        fun builder(): Builder = Builder.new()
    }
}
