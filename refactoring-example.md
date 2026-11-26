# Refactoring example: FeeCalculator.lateFee

Code for the Behavior-Preserving Refactoring slides. Each step is its own package under
`edu.asu.ser316.refactoring`, so every version compiles and runs side by side.

| Package | Slide | What changed |
|---|---|---|
| `step0` | The running example today | Starting point, with the smells |
| `step1` | Step 1, Rename | Parameters and locals renamed, comment removed |
| `step2` | Step 2, Replace magic numbers | Named constants |
| `step3` | Step 3, Extract method | Cap logic moved into `cappedFee` |
| `step4` | Step 4, Replace the type switch | `ItemType` enum with rate and cap |
| `step5` | Step 5, Simplify the flow | Early return for the grace period |
| `unsafe` | Is this step safe? and the AI activity | Grace check moved above the type check |

There is one test file, `src/test/java/edu/asu/ser316/refactoring/FeeCalculatorTest.java`.
Its 12 characterization tests run against every package, so the report shows each test once
per version. The same file, unchanged, passing on step0 to step5 is the proof that behavior
was preserved. It also passes on `unsafe`, which is the point of the trap.

The last test, `unknownTypeIsRejectedEvenWithinGracePeriod`, closes the gap. It is marked
`@Disabled` so the build stays green and the 12 tests can be shown passing first. Remove
`@Disabled` live and it passes on step0 to step5 and fails only on `unsafe`.

The tests use `@ParameterizedTest`, which needs `junit-jupiter-params`. The usual
`org.junit.jupiter:junit-jupiter` dependency already includes it.

Run with `./gradlew test`.

Compare two steps with `git diff --no-index src/main/java/edu/asu/ser316/refactoring/step2 src/main/java/edu/asu/ser316/refactoring/step3`.
