// port-lint: source tokio-util/src/io/mod.rs
package io.github.kotlinmania.tokioutil.io

/**
 * Module ledger for I/O utilities.
 */
internal object IoModLedger {
    val readerStreamClass = ReaderStream::class
    val streamReaderClass = StreamReader::class
    val sinkWriterClass = SinkWriter::class
    val inspectReaderClass = InspectReader::class
    val inspectWriterClass = InspectWriter::class
    val copyToBytesClass = CopyToBytes::class
    val syncIoBridgeClass = SyncIoBridge::class
}
