# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 58/86 (67.4%)
- **Function parity:** 164/862 matched (target 229) — 19.0%
- **Class/type parity:** 39/159 matched (target 80) — 24.5%
- **Combined symbol parity:** 203/1021 matched (target 309) — 19.9%
- **Average inline-code cosine:** 0.24 (function body across 45 matched files)
- **Average documentation cosine:** 0.29 (doc text across 45 matched files)
- **Cheat-zeroed Files:** 17
- **Critical Issues:** 55 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. tokio-util.future

- **Target:** `future.Future`
- **Similarity:** 0.85
- **Dependents:** 11
- **Priority Score:** 11000301.0
- **Functions:** 2/2 matched (target 4)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

### 2. codec.decoder

- **Target:** `codec.Decoder`
- **Similarity:** 0.34
- **Dependents:** 7
- **Priority Score:** 7010306.5
- **Functions:** 1/2 matched (target 1)
- **Missing functions:** `framed`
- **Types:** 1/1 matched
- **Missing types:** _none_

### 3. codec.encoder

- **Target:** `codec.Encoder`
- **Similarity:** 1.00
- **Dependents:** 7
- **Priority Score:** 7000100.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched
- **Missing types:** _none_

### 4. sync.cancellation_token

- **Target:** `cancellationtoken.CancellationToken`
- **Similarity:** 0.25
- **Dependents:** 4
- **Priority Score:** 4061807.5
- **Functions:** 11/16 matched (target 12)
- **Missing functions:** `fmt`, `drop`, `default`, `poll`, `new_future`
- **Types:** 1/2 matched (target 1)
- **Missing types:** `Output`

### 5. time.delay_queue

- **Target:** `time.DelayQueue`
- **Similarity:** 0.05
- **Dependents:** 3
- **Priority Score:** 3425109.5
- **Functions:** 6/39 matched (target 8)
- **Missing functions:** `with_capacity`, `shrink_to_fit`, `compact`, `remap_key`, `create_new_key`, `capacity`, `clear`, `reserve`, `contains`, `fmt`, `index`, `index_mut`, `insert_at`, `poll_expired`, `insert_idx`, `deadline`, `remove_key`, `try_remove`, `reset_at`, `peek`, `next_deadline`, `poll_idx`, `normalize_deadline`, `default`, `poll_next`, `push`, `pop`, `when`, `from`, `get_ref`, `get_mut`, `into_inner`, `key`
- **Types:** 3/12 matched (target 4)
- **Missing types:** `SlabStorage`, `Output`, `KeyInternal`, `Stack`, `Data`, `Item`, `Owned`, `Borrowed`, `Store`

### 6. wheel.stack

- **Target:** `wheel.Stack [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 3
- **Priority Score:** 3000110.0
- **Functions:** 0/0 matched (target 5)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

### 7. io.stream_reader

- **Target:** `io.StreamReader`
- **Similarity:** 0.02
- **Dependents:** 2
- **Priority Score:** 2161809.8
- **Functions:** 1/15 matched (target 2)
- **Missing functions:** `has_chunk`, `into_inner_with_chunk`, `get_ref`, `get_mut`, `get_pin_mut`, `into_inner`, `poll_read`, `poll_fill_buf`, `consume`, `project`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `StreamReaderProject`, `Error`

### 8. util.maybe_dangling

- **Target:** `util.MaybeDangling`
- **Similarity:** 0.15
- **Dependents:** 2
- **Priority Score:** 2050708.5
- **Functions:** 1/4 matched (target 5)
- **Missing functions:** `drop`, `poll`, `maybedangling_runs_drop`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Output`, `SetOnDrop`
- **Tests:** 0/1 matched

### 9. task.join_map

- **Target:** `task.JoinMap`
- **Similarity:** 0.11
- **Dependents:** 1
- **Priority Score:** 1263608.9
- **Functions:** 9/32 matched (target 9)
- **Missing functions:** `with_capacity`, `with_hasher`, `with_capacity_and_hasher`, `capacity`, `spawn_on`, `spawn_blocking`, `spawn_blocking_on`, `spawn_local`, `spawn_local_on`, `insert`, `shutdown`, `keys`, `contains_task`, `reserve`, `shrink_to_fit`, `shrink_to`, `get_by_key`, `remove_by_id`, `detach_all`, `fmt`, `default`, `next`, `size_hint`
- **Types:** 1/4 matched (target 1)
- **Missing types:** `KeySet`, `JoinMapKeys`, `Item`

### 10. task.join_queue

- **Target:** `task.JoinQueue`
- **Similarity:** 0.06
- **Dependents:** 1
- **Priority Score:** 1212809.4
- **Functions:** 6/26 matched (target 6)
- **Missing functions:** `with_capacity`, `spawn_on`, `spawn_local`, `spawn_blocking`, `spawn_blocking_on`, `push_back`, `join_next_with_id`, `try_poll_handle`, `try_join_next`, `try_join_next_with_id`, `shutdown`, `join_all`, `detach_all`, `poll_join_next`, `poll_join_next_with_id`, `fmt`, `default`, `from_iter`, `is_debug`, `assert_debug`
- **Types:** 1/2 matched (target 1)
- **Missing types:** `NotDebug`
- **Tests:** 0/2 matched

### 11. tokio-util.either

- **Target:** `tokioutil.Either`
- **Similarity:** 0.00
- **Dependents:** 1
- **Priority Score:** 1202110.0
- **Functions:** 0/17 matched (target 4)
- **Missing functions:** `poll`, `poll_read`, `poll_fill_buf`, `consume`, `start_seek`, `poll_complete`, `poll_write`, `poll_flush`, `poll_shutdown`, `poll_write_vectored`, `is_write_vectored`, `poll_next`, `poll_ready`, `start_send`, `poll_close`, `either_is_stream`, `either_is_async_read`
- **Types:** 1/4 matched
- **Missing types:** `Output`, `Item`, `Error`

### 12. io.simplex

- **Target:** `io.Simplex`
- **Similarity:** 0.00
- **Dependents:** 1
- **Priority Score:** 1202010.0
- **Functions:** 0/16 matched (target 3)
- **Missing functions:** `with_capacity`, `register_receiver_waker`, `register_sender_waker`, `take_receiver_waker`, `take_sender_waker`, `is_closed`, `close_receiver`, `close_sender`, `drop`, `poll_read`, `poll_write`, `poll_flush`, `poll_shutdown`, `is_write_vectored`, `poll_write_vectored`, `new`
- **Types:** 0/4 matched (target 2)
- **Missing types:** `IoResult`, `Inner`, `Receiver`, `Sender`

### 13. wheel.level

- **Target:** `wheel.Level`
- **Similarity:** 0.27
- **Dependents:** 1
- **Priority Score:** 1081507.4
- **Functions:** 5/13 matched (target 5)
- **Missing functions:** `new`, `next_expiration`, `next_occupied_slot`, `fmt`, `occupied_bit`, `slot_range`, `level_range`, `test_slot_for`
- **Types:** 2/2 matched
- **Missing types:** _none_
- **Tests:** 0/1 matched

### 14. io.copy_to_bytes

- **Target:** `io.CopyToBytes`
- **Similarity:** 0.41
- **Dependents:** 1
- **Priority Score:** 1071105.9
- **Functions:** 4/9 matched (target 5)
- **Missing functions:** `poll_ready`, `start_send`, `poll_flush`, `poll_close`, `poll_next`
- **Types:** 0/2 matched
- **Missing types:** `Error`, `Item`

### 15. io.sink_writer

- **Target:** `io.SinkWriter`
- **Similarity:** 0.27
- **Dependents:** 1
- **Priority Score:** 1071007.2
- **Functions:** 3/9 matched (target 3)
- **Missing functions:** `get_mut`, `poll_write`, `poll_flush`, `poll_shutdown`, `poll_next`, `poll_read`
- **Types:** 0/1 matched
- **Missing types:** `Item`

### 16. io.read_buf

- **Target:** `io.ReadBuf`
- **Similarity:** 0.15
- **Dependents:** 1
- **Priority Score:** 1030408.6
- **Functions:** 1/2 matched (target 1)
- **Missing functions:** `poll`
- **Types:** 0/2 matched (target 0)
- **Missing types:** `ReadBufFn`, `Output`

### 17. codec.bytes_codec

- **Target:** `codec.BytesCodec`
- **Similarity:** 0.44
- **Dependents:** 1
- **Priority Score:** 1020605.6
- **Functions:** 3/3 matched (target 5)
- **Missing functions:** _none_
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Item`, `Error`

### 18. io.reader_stream

- **Target:** `io.ReaderStream`
- **Similarity:** 0.29
- **Dependents:** 1
- **Priority Score:** 1020407.1
- **Functions:** 2/3 matched
- **Missing functions:** `poll_next`
- **Types:** 0/1 matched
- **Missing types:** `Item`

### 19. task.task_tracker

- **Target:** `task.TaskTracker`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 223409.0
- **Functions:** 10/29 matched (target 14)
- **Missing functions:** `is_closed_and_empty`, `set_closed`, `set_open`, `add_task`, `notify_now`, `spawn_on`, `spawn_local`, `spawn_local_on`, `spawn_blocking`, `spawn_blocking_on`, `track_future`, `ptr_eq`, `default`, `clone`, `debug_inner`, `fmt`, `task_tracker`, `drop`, `poll`
- **Types:** 2/5 matched (target 3)
- **Missing types:** `TaskTrackerInner`, `Output`, `Helper`

### 20. sync.mpsc

- **Target:** `sync.Mpsc`
- **Similarity:** 0.03
- **Dependents:** 0
- **Priority Score:** 202509.7
- **Functions:** 3/19 matched (target 8)
- **Missing functions:** `into_inner`, `fmt`, `make_acquire_future`, `empty`, `poll`, `set`, `take_state`, `poll_reserve`, `send_item`, `get_ref`, `abort_send`, `clone`, `poll_ready`, `poll_flush`, `start_send`, `poll_close`
- **Types:** 2/6 matched (target 3)
- **Missing types:** `State`, `InnerFuture`, `PollSenderFuture`, `Error`

### 21. tokio-util.compat

- **Target:** `tokioutil.Compat`
- **Similarity:** 0.11
- **Dependents:** 0
- **Priority Score:** 192208.9
- **Functions:** 3/18 matched (target 3)
- **Missing functions:** `compat`, `compat_write`, `get_mut`, `poll_read`, `poll_fill_buf`, `consume`, `poll_write`, `poll_flush`, `poll_shutdown`, `poll_close`, `poll_seek`, `start_seek`, `poll_complete`, `as_raw_fd`, `as_raw_handle`
- **Types:** 0/4 matched (target 1)
- **Missing types:** `FuturesAsyncReadCompatExt`, `FuturesAsyncWriteCompatExt`, `TokioAsyncReadCompatExt`, `TokioAsyncWriteCompatExt`

### 22. io.sync_bridge

- **Target:** `io.SyncBridge`
- **Similarity:** 0.08
- **Dependents:** 0
- **Priority Score:** 182109.2
- **Functions:** 2/20 matched (target 3)
- **Missing functions:** `fill_buf`, `consume`, `read_until`, `read_line`, `read`, `read_to_end`, `read_to_string`, `read_exact`, `write`, `flush`, `write_all`, `write_vectored`, `seek`, `is_write_vectored`, `shutdown`, `new_with_handle`, `as_mut`, `as_ref`
- **Types:** 1/1 matched
- **Missing types:** _none_

### 23. codec.framed

- **Target:** `codec.Framed`
- **Similarity:** 0.44
- **Dependents:** 0
- **Priority Score:** 152705.6
- **Functions:** 12/24 matched (target 12)
- **Missing functions:** `from_parts`, `get_pin_mut`, `codec_mut`, `map_codec`, `codec_pin_mut`, `into_parts`, `poll_next`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`, `fmt`
- **Types:** 0/3 matched (target 1)
- **Missing types:** `Item`, `Error`, `FramedParts`

### 24. task.spawn_pinned

- **Target:** `task.SpawnPinned`
- **Similarity:** 0.09
- **Dependents:** 0
- **Priority Score:** 141809.1
- **Functions:** 3/11 matched (target 3)
- **Missing functions:** `get_task_loads_for_each_worker`, `spawn_pinned_by_idx`, `fmt`, `find_and_incr_least_burdened_worker`, `find_worker_by_idx`, `drop`, `new_worker`, `run`
- **Types:** 1/7 matched (target 1)
- **Missing types:** `WorkerChoice`, `LocalPool`, `JobCountGuard`, `AbortGuard`, `PinnedFutureSpawner`, `LocalWorkerHandle`

### 25. codec.length_delimited

- **Target:** `codec.LengthDelimited`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 133206.3
- **Functions:** 16/25 matched (target 19)
- **Missing functions:** `decode_data`, `default`, `native_endian`, `length_field_type`, `new_read`, `new_write`, `new_framed`, `adjust_max_frame_len`, `fmt`
- **Types:** 3/7 matched (target 5)
- **Missing types:** `LengthDelimitedCodecError`, `Item`, `Error`, `LengthFieldType`

### 26. codec.framed_write

- **Target:** `codec.FramedWrite`
- **Similarity:** 0.44
- **Dependents:** 0
- **Priority Score:** 132305.6
- **Functions:** 10/21 matched (target 10)
- **Missing functions:** `get_pin_mut`, `encoder_mut`, `map_encoder`, `encoder_pin_mut`, `into_parts`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`, `poll_next`, `fmt`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Error`, `Item`

### 27. codec.framed_read

- **Target:** `codec.FramedRead`
- **Similarity:** 0.38
- **Dependents:** 0
- **Priority Score:** 132106.2
- **Functions:** 8/19 matched (target 8)
- **Missing functions:** `get_pin_mut`, `decoder_mut`, `map_decoder`, `decoder_pin_mut`, `into_parts`, `poll_next`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`, `fmt`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Item`, `Error`

### 28. udp.frame

- **Target:** `udp.Frame`
- **Similarity:** 0.11
- **Dependents:** 0
- **Priority Score:** 131608.9
- **Functions:** 2/13 matched (target 3)
- **Missing functions:** `poll_next`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`, `get_ref`, `get_mut`, `codec`, `codec_mut`, `read_buffer_mut`, `into_inner`
- **Types:** 1/3 matched (target 2)
- **Missing types:** `Item`, `Error`

### 29. wheel.mod

- **Target:** `wheel.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 111710.0
- **Functions:** 5/15 matched (target 5)
- **Missing functions:** `new`, `poll_at`, `peek`, `next_expiration`, `no_expirations_before`, `poll_expiration`, `set_elapsed`, `pop_entry`, `peek_entry`, `test_level_for`
- **Types:** 1/2 matched (target 1)
- **Missing types:** `InsertError`
- **Tests:** 0/1 matched

### 30. codec.framed_impl

- **Target:** `codec.FramedImpl [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 111410.0
- **Functions:** 0/9 matched (target 0)
- **Missing functions:** `default`, `from`, `borrow`, `borrow_mut`, `poll_next`, `poll_ready`, `start_send`, `poll_flush`, `poll_close`
- **Types:** 3/5 matched (target 4)
- **Missing types:** `Item`, `Error`

### 31. task.abort_on_drop

- **Target:** `task.AbortOnDrop`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 101409.3
- **Functions:** 3/11 matched (target 4)
- **Missing functions:** `drop`, `abort_handle`, `detach`, `fmt`, `poll`, `as_ref`, `is_debug`, `assert_debug`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Output`, `NotDebug`
- **Tests:** 0/2 matched

### 32. sync.poll_semaphore

- **Target:** `sync.PollSemaphore`
- **Similarity:** 0.12
- **Dependents:** 0
- **Priority Score:** 101408.8
- **Functions:** 3/12 matched (target 8)
- **Missing functions:** `clone_inner`, `into_inner`, `poll_acquire`, `poll_acquire_many`, `add_permits`, `poll_next`, `clone`, `fmt`, `as_ref`
- **Types:** 1/2 matched (target 1)
- **Missing types:** `Item`

### 33. sync.reusable_box

- **Target:** `sync.ReusableBox`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 101309.0
- **Functions:** 2/10 matched (target 3)
- **Missing functions:** `try_set`, `real_try_set`, `get_pin`, `poll`, `fmt`, `reuse_pin_box`, `call`, `drop`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Output`, `CallOnDrop`

### 34. codec.lines_codec

- **Target:** `codec.LinesCodec`
- **Similarity:** 0.34
- **Dependents:** 0
- **Priority Score:** 81506.6
- **Functions:** 6/11 matched (target 6)
- **Missing functions:** `utf8`, `without_carriage_return`, `default`, `fmt`, `from`
- **Types:** 1/4 matched (target 1)
- **Missing types:** `Item`, `Error`, `LinesCodecError`

### 35. io.inspect

- **Target:** `io.Inspect`
- **Similarity:** 0.09
- **Dependents:** 0
- **Priority Score:** 70809.1
- **Functions:** 1/8 matched (target 4)
- **Missing functions:** `into_inner`, `poll_read`, `poll_write`, `poll_flush`, `poll_shutdown`, `poll_write_vectored`, `is_write_vectored`
- **Types:** 0/0 matched (target 2)
- **Missing types:** _none_

### 36. tests.loom_cancellation_token

- **Target:** `tests.LoomCancellationToken [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 70710.0
- **Functions:** 0/7 matched (target 0)
- **Missing functions:** `cancel_token`, `cancel_token_owned`, `cancel_with_child`, `drop_token_no_child`, `drop_token_with_children`, `drop_and_cancel_token`, `cancel_parent_and_child`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 0/7 matched

### 37. cancellation_token.tree_node

- **Target:** `cancellationtoken.TreeNode`
- **Similarity:** 0.22
- **Dependents:** 0
- **Priority Score:** 61307.8
- **Functions:** 6/11 matched (target 10)
- **Missing functions:** `notified`, `disconnect_children`, `with_locked_node_and_parent`, `move_children_to_parent`, `remove_child`
- **Types:** 1/2 matched (target 1)
- **Missing types:** `Inner`

### 38. codec.any_delimiter_codec

- **Target:** `codec.AnyDelimiterCodec`
- **Similarity:** 0.47
- **Dependents:** 0
- **Priority Score:** 61305.3
- **Functions:** 6/9 matched (target 6)
- **Missing functions:** `default`, `fmt`, `from`
- **Types:** 1/4 matched (target 1)
- **Missing types:** `Item`, `Error`, `AnyDelimiterCodecError`

### 39. net.mod

- **Target:** `net.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 60910.0
- **Functions:** 2/4 matched (target 2)
- **Missing functions:** `poll_accept`, `poll`
- **Types:** 1/5 matched (target 1)
- **Missing types:** `Io`, `Addr`, `ListenerAcceptFut`, `Output`

### 40. tokio-util.context

- **Target:** `tokioutil.Context`
- **Similarity:** 0.06
- **Dependents:** 0
- **Priority Score:** 60709.4
- **Functions:** 1/5 matched (target 2)
- **Missing functions:** `handle`, `into_inner`, `poll`, `wrap`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Output`, `RuntimeExt`

### 41. unix.mod

- **Target:** `unix.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 40410.0
- **Functions:** 0/2 matched (target 0)
- **Missing functions:** `poll_accept`, `local_addr`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Io`, `Addr`

### 42. future.with_cancellation_token

- **Target:** `future.WithCancellationToken`
- **Similarity:** 0.34
- **Dependents:** 0
- **Priority Score:** 20306.6
- **Functions:** 1/2 matched (target 4)
- **Missing functions:** `poll`
- **Types:** 0/1 matched (target 2)
- **Missing types:** `Output`

### 43. time.mod

- **Target:** `time.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 20210.0
- **Functions:** 0/1 matched (target 0)
- **Missing functions:** `ms`
- **Types:** 0/1 matched
- **Missing types:** `Round`

### 44. cancellation_token.guard

- **Target:** `cancellationtoken.Guard`
- **Similarity:** 0.32
- **Dependents:** 0
- **Priority Score:** 10306.8
- **Functions:** 1/2 matched (target 3)
- **Missing functions:** `drop`
- **Types:** 1/1 matched
- **Missing types:** _none_

### 45. cancellation_token.guard_ref

- **Target:** `cancellationtoken.GuardRef`
- **Similarity:** 0.32
- **Dependents:** 0
- **Priority Score:** 10306.8
- **Functions:** 1/2 matched (target 3)
- **Missing functions:** `drop`
- **Types:** 1/1 matched
- **Missing types:** _none_

### 46. io.read_arc

- **Target:** `io.ReadArc`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10110.0
- **Functions:** 0/1 matched
- **Missing functions:** `read_exact_arc`
- **Types:** 0/0 matched
- **Missing types:** _none_

### 47. util.poll_buf

- **Target:** `util.PollBuf`
- **Similarity:** 0.42
- **Dependents:** 0
- **Priority Score:** 205.8
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

### 48. tokio-util.tracing

- **Target:** `tokioutil.Tracing [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 1)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 49. task.mod

- **Target:** `task.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 50. util.mod

- **Target:** `util.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 51. sync.mod

- **Target:** `sync.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 52. codec.mod

- **Target:** `codec.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 53. tests.mod

- **Target:** `tests.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 54. udp.mod

- **Target:** `udp.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 55. tokio-util.loom

- **Target:** `tokioutil.Loom [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 1)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 56. io.mod

- **Target:** `io.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 57. tokio-util.lib

- **Target:** `tokioutil.Lib [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 58. tokio-util.cfg

- **Target:** `tokioutil.Cfg`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

