// port-lint: source time/wheel/level.rs
package io.github.kotlinmania.tokioutil.time.wheel

internal data class Expiration(
    val level: Int,
    val slot: Int,
    val deadline: Long,
)

internal class Level<T>(
    val level: Int,
) {
    private var occupied: Long = 0L
    private val slots: Array<ListStack<T>> = Array(LEVEL_MULT) { ListStack() }

    fun addEntry(whenMs: Long, item: T) {
        val slot = slotFor(whenMs)
        slots[slot].push(item)
        occupied = occupied or (1L shl slot)
    }

    fun removeEntry(whenMs: Long, item: T) {
        val slot = slotFor(whenMs)
        slots[slot].remove(item)
        if (slots[slot].isEmpty()) {
            occupied = occupied and (1L shl slot).inv()
        }
    }

    fun popEntrySlot(slot: Int): T? {
        val item = slots[slot].pop()
        if (slots[slot].isEmpty()) {
            occupied = occupied and (1L shl slot).inv()
        }
        return item
    }

    fun peekEntrySlot(slot: Int): T? = slots[slot].peek()

    private fun slotFor(whenMs: Long): Int {
        return ((whenMs ushr (level * 6)) and 63L).toInt()
    }

    companion object {
        const val LEVEL_MULT: Int = 64
    }
}
