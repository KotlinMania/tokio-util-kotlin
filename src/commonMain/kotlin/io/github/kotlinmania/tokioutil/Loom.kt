// port-lint: source loom.rs
package io.github.kotlinmania.tokioutil

import kotlinx.coroutines.sync.Mutex

/**
 * Synchronization abstraction primitives.
 */
internal object LoomSync {
    internal fun createMutex(): Mutex = Mutex()
}
