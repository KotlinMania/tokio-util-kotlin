// port-lint: source codec/encoder.rs
package io.github.kotlinmania.tokioutil.codec

import io.github.kotlinmania.tokioutil.bytes.BytesMut

/**
 * Trait of helper objects to write out messages as bytes.
 */
internal interface Encoder<Item> {
    /**
     * Encodes a frame into the buffer provided by [dst].
     */
    fun encode(item: Item, dst: BytesMut)
}
