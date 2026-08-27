// port-lint: source tokio-util/src/io/read_arc.rs
package io.github.kotlinmania.tokioutil.io

/**
 * Reads exact number of bytes into a [ByteArray].
 */
suspend fun readExact(
    readFn: suspend (ByteArray, Int, Int) -> Int,
    len: Int,
): ByteArray {
    val buf = ByteArray(len)
    var readTotal = 0
    while (readTotal < len) {
        val n = readFn(buf, readTotal, len - readTotal)
        if (n <= 0) {
            throw Exception("early EOF")
        }
        readTotal += n
    }
    return buf
}
