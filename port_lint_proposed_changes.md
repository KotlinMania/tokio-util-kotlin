# port-lint Proposed Changes

**Generated:** 2026-08-26
**Source:** tmp
**Target:** src/commonMain/kotlin

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/tokioutil/future/Future.kt` | `// port-lint: source tokio-util/tests/future.rs` | `// port-lint: source future.rs` | `future.rs` | `port-lint provenance header matched only by basename: 'tokio-util/tests/future.rs' vs expected 'future.rs'` |
