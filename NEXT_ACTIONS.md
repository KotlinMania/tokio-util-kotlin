# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 1/58 (1.7%)
- **Function parity:** 9/578 matched (target 21) — 1.6%
- **Class/type parity:** 0/117 matched (target 6) — 0.0%
- **Combined symbol parity:** 9/695 matched (target 27) — 1.3%
- **Average inline-code cosine:** 0.68 (function body across 1 matched files)
- **Average documentation cosine:** 0.73 (doc text across 1 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 0 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. io.copy_to_bytes

- **Target:** `io.CopyToBytes`
- **Similarity:** 0.68
- **Dependents:** 1
- **Priority Score:** 1021103.2
- **Functions:** 9/9 matched (target 21)
- **Missing functions:** _none_
- **Types:** 0/2 matched (target 6)
- **Missing types:** `Error`, `Item`

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `codec.mod` | `codec.Mod` | 0 | `codec/mod.rs` | `codec/Mod.kt` |
| `io.mod` | `io.Mod` | 0 | `io/mod.rs` | `io/Mod.kt` |
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |
| `net.mod` | `net.Mod` | 0 | `net/mod.rs` | `net/Mod.kt` |
| `unix.mod` | `net.unix.Mod` | 0 | `net/unix/mod.rs` | `net/unix/Mod.kt` |
| `sync.mod` | `sync.Mod` | 0 | `sync/mod.rs` | `sync/Mod.kt` |
| `tests.mod` | `sync.tests.Mod` | 0 | `sync/tests/mod.rs` | `sync/tests/Mod.kt` |
| `task.mod` | `task.Mod` | 0 | `task/mod.rs` | `task/Mod.kt` |
| `time.mod` | `time.Mod` | 0 | `time/mod.rs` | `time/Mod.kt` |
| `wheel.mod` | `time.wheel.Mod` | 0 | `time/wheel/mod.rs` | `time/wheel/Mod.kt` |
| `udp.mod` | `udp.Mod` | 0 | `udp/mod.rs` | `udp/Mod.kt` |
| `util.mod` | `util.Mod` | 0 | `util/mod.rs` | `util/Mod.kt` |

