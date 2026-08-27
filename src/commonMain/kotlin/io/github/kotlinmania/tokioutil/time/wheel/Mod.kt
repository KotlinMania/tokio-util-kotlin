// port-lint: source time/wheel/mod.rs
package io.github.kotlinmania.tokioutil.time.wheel

/**
 * Timing wheel implementation.
 */
internal class Wheel<T> {
    private var elapsed: Long = 0L
    private val levels: Array<Level<T>> = Array(NUM_LEVELS) { Level(it) }

    fun elapsed(): Long = elapsed

    fun insert(whenMs: Long, item: T) {
        if (whenMs <= elapsed) {
            levels[0].addEntry(whenMs, item)
            return
        }
        val level = levelFor(elapsed, whenMs)
        levels[level].addEntry(whenMs, item)
    }

    fun remove(whenMs: Long, item: T) {
        val level = levelFor(elapsed, whenMs)
        levels[level].removeEntry(whenMs, item)
    }

    fun poll(nowMs: Long): T? {
        if (nowMs < elapsed) return null
        elapsed = nowMs
        for (lvl in levels) {
            for (slot in 0 until Level.LEVEL_MULT) {
                val item = lvl.popEntrySlot(slot)
                if (item != null) return item
            }
        }
        return null
    }

    private fun levelFor(elapsed: Long, whenMs: Long): Int {
        val diff = (elapsed xor whenMs) or 63L
        val leadingZeros = diff.countLeadingZeroBits()
        val significant = 63 - leadingZeros
        return minOf(NUM_LEVELS - 1, significant / 6)
    }

    companion object {
        const val NUM_LEVELS: Int = 6
    }
}
