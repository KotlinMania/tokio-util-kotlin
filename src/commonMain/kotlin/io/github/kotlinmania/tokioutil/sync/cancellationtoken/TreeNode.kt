// port-lint: source sync/cancellation_token/tree_node.rs
@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.github.kotlinmania.tokioutil.sync.cancellationtoken

import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicReference
import kotlinx.coroutines.CompletableDeferred

/**
 * A node of the cancellation tree structure.
 */
class TreeNode {
    private val isCancelled = AtomicBoolean(false)
    internal val internalDeferred = CompletableDeferred<Unit>()

    private var parent: TreeNode? = null
    private val children = AtomicReference<List<TreeNode>>(emptyList())
    private val numHandles = AtomicInt(1)

    fun isCancelledSync(): Boolean = isCancelled.load()

    fun cancelSync() {
        if (!isCancelled.compareAndSet(expectedValue = false, newValue = true)) {
            return
        }
        internalDeferred.complete(Unit)
        val copy = children.exchange(emptyList())
        parent = null
        for (child in copy) {
            child.cancelSync()
        }
    }

    fun createChildSync(): TreeNode {
        val child = TreeNode()
        if (isCancelled.load()) {
            child.cancelSync()
            return child
        }
        child.parent = this
        while (true) {
            if (isCancelled.load()) {
                child.cancelSync()
                return child
            }
            val current = children.load()
            val next = current + listOf(child)
            if (children.compareAndSet(current, next)) {
                if (isCancelled.load()) {
                    child.cancelSync()
                }
                break
            }
        }
        return child
    }

    fun increaseHandleRefcount() {
        numHandles.addAndFetch(1)
    }

    fun decreaseHandleRefcount() {
        val count = numHandles.addAndFetch(-1)
        if (count == 0 && !isCancelled.load()) {
            val p = parent
            if (p != null) {
                while (true) {
                    val current = p.children.load()
                    val next = current.filter { it !== this }
                    if (p.children.compareAndSet(current, next)) {
                        break
                    }
                }
                parent = null
            }
        }
    }

    suspend fun awaitCancellation() {
        internalDeferred.await()
    }

    companion object {
        fun new(): TreeNode = TreeNode()

        fun isCancelled(node: TreeNode): Boolean = node.isCancelledSync()

        fun childNode(parent: TreeNode): TreeNode = parent.createChildSync()

        fun cancel(node: TreeNode) {
            node.cancelSync()
        }
    }
}
