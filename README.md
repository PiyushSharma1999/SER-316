# SER 316 Testing Examples

Runnable Java examples for the black-box and white-box testing lectures.
The code and specs match the slides, including the planted bugs.

## Run

    ./gradlew test

Or open the folder in IntelliJ (File > Open) and run any test class.
The JaCoCo coverage report is written to `build/reports/jacoco/test/html/index.html`.

## Examples

| Package | Slides | What it shows |
|---|---|---|
| `shipping` | Black box: good tests, boundaries. White box: motivation, CFG, mutation | Partitions and boundaries for the $50 rule. The `express` branch is not in the spec, so black-box tests never reach it |
| `store` | Foundations: tests check state, not only return values | `placeOrder` changes the order history and the stock |
| `enrollment` | Black box: state, enrollment activity. White box: branches that depend on state | Inputs that come from system state, and tests that check state |
| `bonus` (hidden) | Black box: computeBonus, same tests on three implementations. Foundations: the two-model activity | Spec-only example. Students never see the code |
| `grades` | White box: control flow graphs, coverage criteria, findAverage activity | Statement, branch and loop coverage |
| `formatting` | White box: when white box catches what black box misses | `pretty(5000)` returns "50 thousand" |
| `parity` | White box: when black box catches what white box misses | `evenOrOdd(3)` returns 1, not -1 |

`OrderWeakTest` holds the intentionally weak tests from the slides, for the
weak-assertion and mutation-testing discussions.

## Planted bugs

Tests that expose a planted bug are marked `@Disabled`, so the normal build
stays green. Remove `@Disabled` in the lecture and rerun to watch them fail.

## Spec-only example (computeBonus)

Students get only the requirement. The implementations live in `bonus-hidden`.
Pick which one to test:

    ./gradlew test --tests '*BonusCalculatorTest' -PbonusImpl=A

In IntelliJ, add `-Dbonus.impl=A` to the run configuration's VM options.

- A: exactly 3 years earns 50% (should be 0%)
- B: 70 years is accepted (should throw InvalidData)
- C: correct (the default)

### Student version

1. Run `./gradlew :bonus-hidden:jar`.
2. Copy `bonus-hidden/build/libs/bonus-hidden.jar` to `libs/bonus-hidden.jar`.
3. Delete the `bonus-hidden` folder and the `include 'bonus-hidden'` line in `settings.gradle`.
4. In `build.gradle`, replace `testImplementation project(':bonus-hidden')`
   with `testImplementation files('libs/bonus-hidden.jar')`.
