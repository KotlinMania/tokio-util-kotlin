// port-lint: source tokio-util/src/tracing.rs
package io.github.kotlinmania.tokioutil

/**
 * Tracing helper utility for tokio-util.
 */
internal object Tracing {
    inline fun trace(message: () -> String) {
        // Tracing helper matching upstream macro
    }
}
