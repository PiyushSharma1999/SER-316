package edu.asu.ser316.testing.bonus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Spec-only example. Students see the requirement, never the code.
 *
 * Requirement: more than 3 years earns 50%, more than 5 earns 75%,
 * more than 8 earns 100%. Years must be positive and less than 70,
 * otherwise InvalidData is thrown.
 *
 * Choose the implementation to test:
 *   ./gradlew test --tests '*BonusCalculatorTest' -PbonusImpl=A
 * In IntelliJ, add  -Dbonus.impl=A  to the run configuration's VM options.
 */
class BonusCalculatorTest {

    private BonusCalculator calc;

    @BeforeEach
    void pickImplementation() {
        calc = BonusCalculator.forName(System.getProperty("bonus.impl", "C"));
    }

    @ParameterizedTest(name = "{0} years earns {1}%")
    @CsvSource({
        // partition representatives
        "2, 0",
        "4, 50",
        "7, 75",
        "30, 100",
        // boundaries
        "1, 0",
        "3, 0",    // "more than 3" excludes 3
        "5, 50",
        "6, 75",
        "8, 75",
        "9, 100",
        "69, 100"  // last valid value
    })
    void validYearsEarnTheRightBonus(int years, int expectedPercent) throws InvalidData {
        assertEquals(expectedPercent, calc.computeBonus(years));
    }

    @ParameterizedTest(name = "{0} years is rejected")
    @ValueSource(ints = {-6, 0, 70, 80})
    void yearsOutsideOneToSixtyNineAreRejected(int years) {
        assertThrows(InvalidData.class, () -> calc.computeBonus(years));
    }
}
