# port-lint Proposed Changes

**Generated:** 2026-08-27
**Source:** tmp/tokio-util/src
**Target:** src/commonMain/kotlin/io/github/kotlinmania/tokioutil

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonTest/kotlin/io/github/kotlinmania/tokioutil/task/TaskTest.kt` | `// port-lint: tests task_tracker.rs` | `// port-lint: tests task/task_tracker.rs` | `task/task_tracker.rs` | `port-lint provenance header matched only by basename: 'tests:task_tracker.rs' vs expected 'task/task_tracker.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/tokioutil/sync/SyncTest.kt` | `// port-lint: tests mpsc.rs` | `// port-lint: tests sync/mpsc.rs` | `sync/mpsc.rs` | `port-lint provenance header matched only by basename: 'tests:mpsc.rs' vs expected 'sync/mpsc.rs'` |
