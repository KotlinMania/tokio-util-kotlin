// port-lint: source tokio-util/src/lib.rs
package io.github.kotlinmania.tokioutil

import io.github.kotlinmania.tokioutil.codec.CodecModLedger
import io.github.kotlinmania.tokioutil.future.FutureModLedger
import io.github.kotlinmania.tokioutil.io.IoModLedger
import io.github.kotlinmania.tokioutil.sync.SyncModLedger
import io.github.kotlinmania.tokioutil.task.TaskModLedger
import io.github.kotlinmania.tokioutil.time.TimeModLedger
import io.github.kotlinmania.tokioutil.udp.UdpModLedger
import io.github.kotlinmania.tokioutil.util.UtilModLedger

/**
 * Root module ledger for tokio-util-kotlin.
 */
internal object TokioUtilModLedger {
    val codec = CodecModLedger
    val future = FutureModLedger
    val io = IoModLedger
    val sync = SyncModLedger
    val task = TaskModLedger
    val time = TimeModLedger
    val udp = UdpModLedger
    val util = UtilModLedger
    val eitherClass = Either::class
    val contextClass = TokioContext::class
    val compatClass = Compat::class
}
