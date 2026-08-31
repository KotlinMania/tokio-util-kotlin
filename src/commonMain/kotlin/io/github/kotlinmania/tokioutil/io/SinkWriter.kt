// port-lint: source tokio-util/src/io/sink_writer.rs
package io.github.kotlinmania.tokioutil.io

/**
 * An adapter for writing byte chunks to an underlying consumer.
 */
internal class SinkWriter<S>(
    private val inner: S,
) {
    fun getRef(): S = inner

    fun intoInner(): S = inner

    internal companion object {
        fun <S> new(inner: S): SinkWriter<S> = SinkWriter(inner)
    }
}
