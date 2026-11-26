package edu.asu.ser316.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Characterization tests. They record what lateFee does today, before any
 * refactoring. This one file runs, unchanged, against every version of the code.
 */
class FeeCalculatorTest {

    /** Any version of lateFee. Each step's FeeCalculator fits it as is. */
    interface LateFee {
        double lateFee(String itemType, int daysLate, boolean member);
    }

    static Stream<Named<LateFee>> versions() {
        return Stream.of(
                Named.of("step0", new edu.asu.ser316.refactoring.step0.FeeCalculator()::lateFee),
                Named.of("step1", new edu.asu.ser316.refactoring.step1.FeeCalculator()::lateFee),
                Named.of("step2", new edu.asu.ser316.refactoring.step2.FeeCalculator()::lateFee),
                Named.of("step3", new edu.asu.ser316.refactoring.step3.FeeCalculator()::lateFee),
                Named.of("step4", new edu.asu.ser316.refactoring.step4.FeeCalculator()::lateFee),
                Named.of("step5", new edu.asu.ser316.refactoring.step5.FeeCalculator()::lateFee),
                Named.of("unsafe", new edu.asu.ser316.refactoring.unsafe.FeeCalculator()::lateFee));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void bookTenDaysLate(LateFee calc) {
        assertEquals(2.50, calc.lateFee("BOOK", 10, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void bookFeeIsCappedAtTen(LateFee calc) {
        assertEquals(10.00, calc.lateFee("BOOK", 50, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void dvdFiveDaysLate(LateFee calc) {
        assertEquals(5.00, calc.lateFee("DVD", 5, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void dvdFeeIsCappedAtTen(LateFee calc) {
        assertEquals(10.00, calc.lateFee("DVD", 30, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void laptopThreeDaysLate(LateFee calc) {
        assertEquals(15.00, calc.lateFee("LAPTOP", 3, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void laptopFeeIsCappedAtFifty(LateFee calc) {
        assertEquals(50.00, calc.lateFee("LAPTOP", 20, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void twoDaysLateIsFree(LateFee calc) {
        assertEquals(0.00, calc.lateFee("LAPTOP", 2, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void threeDaysLateIsCharged(LateFee calc) {
        assertEquals(0.75, calc.lateFee("BOOK", 3, false), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void membersPayHalf(LateFee calc) {
        assertEquals(2.50, calc.lateFee("DVD", 5, true), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void memberDiscountAppliesAfterTheCap(LateFee calc) {
        assertEquals(25.00, calc.lateFee("LAPTOP", 20, true), 0.001);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void negativeDaysAreRejected(LateFee calc) {
        assertThrows(IllegalArgumentException.class, () -> calc.lateFee("BOOK", -1, false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void unknownTypeIsRejected(LateFee calc) {
        assertThrows(IllegalArgumentException.class, () -> calc.lateFee("MAGAZINE", 5, false));
    }

    // Added after the order-of-checks problem was found. Disabled so all 12
    // tests can be shown passing on every version first. Remove @Disabled to
    // see step0 to step5 pass and unsafe fail.
    @Disabled("Remove to show the gap")
    @ParameterizedTest(name = "{0}")
    @MethodSource("versions")
    void unknownTypeIsRejectedEvenWithinGracePeriod(LateFee calc) {
        assertThrows(IllegalArgumentException.class, () -> calc.lateFee("MAGAZINE", 1, false));
    }
}
