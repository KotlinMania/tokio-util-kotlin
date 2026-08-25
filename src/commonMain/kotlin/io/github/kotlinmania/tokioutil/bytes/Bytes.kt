// port-lint: source bytes.rs
package io.github.kotlinmania.tokioutil.bytes

/**
 * An immutable sequence of bytes.
 */
class Bytes private constructor(
    private val data: ByteArray,
    private val offset: Int,
    private val length: Int,
) {
    val size: Int get() = length

    fun len(): Int = length

    fun isEmpty(): Boolean = length == 0

    fun isNotEmpty(): Boolean = length > 0

    operator fun get(index: Int): Byte {
        require(index in 0 until length) { "Index $index out of bounds for size $length" }
        return data[offset + index]
    }

    fun toByteArray(): ByteArray = data.copyOfRange(offset, offset + length)

    fun slice(start: Int, end: Int): Bytes {
        require(start in 0..length && end in start..length) {
            "Invalid slice range [$start, $end) for length $length"
        }
        return Bytes(data, offset + start, end - start)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bytes) return false
        if (length != other.length) return false
        for (i in 0 until length) {
            if (this[i] != other[i]) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = 1
        for (i in 0 until length) {
            result = 31 * result + this[i].toInt()
        }
        return result
    }

    override fun toString(): String =
        "Bytes(len=$length, content=[${(0 until minOf(length, 16)).joinToString(", ") { this[it].toString() }}${if (length > 16) "..." else ""}])"

    companion object {
        val EMPTY: Bytes = Bytes(ByteArray(0), 0, 0)

        fun copyFromSlice(bytes: ByteArray, offset: Int = 0, length: Int = bytes.size - offset): Bytes {
            require(offset >= 0 && length >= 0 && offset + length <= bytes.size)
            val copy = bytes.copyOfRange(offset, offset + length)
            return Bytes(copy, 0, copy.size)
        }

        fun from(bytes: ByteArray): Bytes = copyFromSlice(bytes)

        fun fromStatic(bytes: ByteArray): Bytes = copyFromSlice(bytes)
    }
}

/**
 * A mutable, growable buffer of bytes.
 */
class BytesMut(
    initialCapacity: Int = 64,
) {
    private var buf: ByteArray = ByteArray(initialCapacity.coerceAtLeast(0))
    private var len: Int = 0

    val size: Int get() = len

    fun len(): Int = len

    fun capacity(): Int = buf.size

    fun isEmpty(): Boolean = len == 0

    fun isNotEmpty(): Boolean = len > 0

    fun reserve(additional: Int) {
        if (additional <= 0) return
        val required = len + additional
        if (required > buf.size) {
            var newCap = (buf.size * 2).coerceAtLeast(64)
            while (newCap < required) {
                newCap *= 2
            }
            val newBuf = ByteArray(newCap)
            buf.copyInto(newBuf, 0, 0, len)
            buf = newBuf
        }
    }

    fun put(byte: Byte) {
        reserve(1)
        buf[len++] = byte
    }

    fun put(bytes: ByteArray, offset: Int = 0, length: Int = bytes.size - offset) {
        require(offset >= 0 && length >= 0 && offset + length <= bytes.size)
        reserve(length)
        bytes.copyInto(buf, len, offset, offset + length)
        len += length
    }

    fun put(bytes: Bytes) {
        put(bytes.toByteArray())
    }

    fun put(bytes: BytesMut) {
        put(bytes.toByteArray())
    }

    operator fun get(index: Int): Byte {
        require(index in 0 until len) { "Index $index out of bounds for len $len" }
        return buf[index]
    }

    operator fun set(index: Int, value: Byte) {
        require(index in 0 until len) { "Index $index out of bounds for len $len" }
        buf[index] = value
    }

    fun splitTo(at: Int): BytesMut {
        require(at in 0..len) { "splitTo index $at out of bounds for len $len" }
        val prefix = BytesMut(at)
        prefix.put(buf, 0, at)
        val remaining = len - at
        buf.copyInto(buf, 0, at, len)
        len = remaining
        return prefix
    }

    fun splitOff(at: Int): BytesMut {
        require(at in 0..len) { "splitOff index $at out of bounds for len $len" }
        val suffix = BytesMut(len - at)
        suffix.put(buf, at, len - at)
        len = at
        return suffix
    }

    fun split(): BytesMut = splitTo(len)

    fun truncate(newLen: Int) {
        if (newLen < len) {
            len = newLen.coerceAtLeast(0)
        }
    }

    fun clear() {
        len = 0
    }

    fun toByteArray(): ByteArray = buf.copyOfRange(0, len)

    fun freeze(): Bytes = Bytes.copyFromSlice(buf, 0, len)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BytesMut) return false
        if (len != other.len) return false
        for (i in 0 until len) {
            if (buf[i] != other.buf[i]) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = 1
        for (i in 0 until len) {
            result = 31 * result + buf[i].toInt()
        }
        return result
    }

    override fun toString(): String =
        "BytesMut(len=$len, cap=${buf.size})"

    companion object {
        fun new(): BytesMut = BytesMut()

        fun withCapacity(capacity: Int): BytesMut = BytesMut(capacity)

        fun from(bytes: ByteArray): BytesMut {
            val bm = BytesMut(bytes.size)
            bm.put(bytes)
            return bm
        }
    }
}
