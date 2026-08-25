@file:OptIn(kotlinx.coroutines.DelicateCoroutinesApi::class)

package io.github.kotlinmania.tokioutil.sync

import kotlinx.coroutines.channels.Channel

/**
 * Error returned when the channel is closed.
 */
internal class PollSendError(
    val item: Any? = null,
) : Exception("channel closed")

/**
 * A wrapper around a multi-producer, single-consumer channel.
 */
internal class PollSender<T>(
    capacity: Int = Channel.BUFFERED,
) {
    private val channel = Channel<T>(capacity)

    internal fun isClosed(): Boolean = channel.isClosedForSend

    internal fun close() {
        channel.close()
    }

    internal suspend fun send(item: T) {
        if (isClosed()) {
            throw PollSendError(item)
        }
        try {
            channel.send(item)
        } catch (e: Exception) {
            throw PollSendError(item)
        }
    }

    internal fun trySend(item: T): Result<Unit> {
        val result = channel.trySend(item)
        return if (result.isSuccess) {
            Result.success(Unit)
        } else {
            Result.failure(PollSendError(item))
        }
    }

    internal fun getChannel(): Channel<T> = channel

    internal companion object {
        fun <T> new(capacity: Int = Channel.BUFFERED): PollSender<T> =
            PollSender(capacity)
    }
}
