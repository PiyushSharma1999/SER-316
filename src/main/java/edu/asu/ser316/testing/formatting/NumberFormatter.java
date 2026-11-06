package edu.asu.ser316.testing.formatting;

/**
 * Requirement: pretty returns an integer as readable text.
 * Numbers over 1000 are shown in thousands, for example 5000 becomes "5 thousand".
 *
 * Planted bug: the divisor should be 1000, not 100.
 * A black-box test with one typical value (42) never reaches it.
 */
public final class NumberFormatter {

    private NumberFormatter() {
    }

    public static String pretty(int number) {
        if (number > 1000)
            return (number / 100) + " thousand";
        else
            return String.valueOf(number);
    }
}
