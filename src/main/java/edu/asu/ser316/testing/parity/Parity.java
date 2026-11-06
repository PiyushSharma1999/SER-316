package edu.asu.ser316.testing.parity;

/**
 * Requirement: evenOrOdd returns 0 if the number is even and -1 if it is odd.
 *
 * Planted bug: for odd numbers this returns 1, not -1 (and -1 for negative odd numbers).
 * One test with 4 gives 100% coverage and passes, so coverage alone misses it.
 */
public final class Parity {

    private Parity() {
    }

    public static int evenOrOdd(int number) {
        return number % 2;
    }
}
