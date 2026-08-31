// port-lint: source tokio-util/src/codec/mod.rs
package io.github.kotlinmania.tokioutil.codec

/**
 * Module ledger for codec components.
 */
internal object CodecModLedger {
    val decoderClass = Decoder::class
    val encoderClass = Encoder::class
    val bytesCodecClass = BytesCodec::class
    val linesCodecClass = LinesCodec::class
    val anyDelimiterCodecClass = AnyDelimiterCodec::class
    val lengthDelimitedCodecClass = LengthDelimitedCodec::class
    val framedClass = Framed::class
    val framedReadClass = FramedRead::class
    val framedWriteClass = FramedWrite::class
}
