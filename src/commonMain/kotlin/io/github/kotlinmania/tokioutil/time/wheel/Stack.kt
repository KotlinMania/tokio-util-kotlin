// port-lint: source time/wheel/stack.rs
package io.github.kotlinmania.tokioutil.time.wheel

/**
 * Stack abstraction for timing wheel entries.
 */
internal interface Stack<T> {
    fun isEmpty(): Boolean
    fun push(item: T)
    fun pop(): T?
    fun peek(): T?
    fun remove(item: T)
}

internal class ListStack<T> : Stack<T> {
    private val items = ArrayDeque<T>()

    override fun isEmpty(): Boolean = items.isEmpty()

    override fun push(item: T) {
        items.addLast(item)
    }

    override fun pop(): T? = items.removeLastOrNull()

    override fun peek(): T? = items.lastOrNull()

    override fun remove(item: T) {
        items.remove(item)
    }
}
