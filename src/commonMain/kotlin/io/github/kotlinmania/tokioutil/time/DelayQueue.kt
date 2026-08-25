package io.github.kotlinmania.tokioutil.time

import kotlinx.coroutines.delay
import kotlin.time.ComparableTimeMark
import kotlin.time.Duration
import kotlin.time.TimeSource

/**
 * A key representing an entry in a [DelayQueue].
 */
data class Key(
    val id: Long,
)

/**
 * An expired entry from a [DelayQueue].
 */
internal data class Expired<T>(
    val key: Key,
    val value: T,
)

/**
 * A queue of delayed elements.
 */
internal class DelayQueue<T> {
    private var nextKeyId: Long = 0L
    private val entries = mutableMapOf<Key, DelayedEntry<T>>()

    private data class DelayedEntry<T>(
        val key: Key,
        val value: T,
        val deadline: ComparableTimeMark,
    )

    fun len(): Int = entries.size

    fun isEmpty(): Boolean = entries.isEmpty()

    fun isNotEmpty(): Boolean = entries.isNotEmpty()

    fun insert(value: T, delay: Duration): Key {
        val key = Key(nextKeyId++)
        val deadline = TimeSource.Monotonic.markNow() + delay
        entries[key] = DelayedEntry(key, value, deadline)
        return key
    }

    fun remove(key: Key): T? = entries.remove(key)?.value

    fun reset(key: Key, delay: Duration) {
        val entry = entries[key] ?: return
        val newDeadline = TimeSource.Monotonic.markNow() + delay
        entries[key] = entry.copy(deadline = newDeadline)
    }

    suspend fun nextExpired(): Expired<T>? {
        if (entries.isEmpty()) return null
        while (entries.isNotEmpty()) {
            val now = TimeSource.Monotonic.markNow()
            var earliest: DelayedEntry<T>? = null
            for (entry in entries.values) {
                if (earliest == null || entry.deadline < earliest.deadline) {
                    earliest = entry
                }
            }
            if (earliest == null) return null
            if (earliest.deadline <= now) {
                entries.remove(earliest.key)
                return Expired(earliest.key, earliest.value)
            }
            val remaining = earliest.deadline - now
            delay(remaining)
        }
        return null
    }

    companion object {
        fun <T> new(): DelayQueue<T> = DelayQueue()
    }
}
