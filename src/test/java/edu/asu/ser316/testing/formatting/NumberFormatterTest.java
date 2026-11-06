package edu.asu.ser316.testing.formatting;

import static edu.asu.ser316.testing.formatting.NumberFormatter.pretty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class NumberFormatterTest {

    @Test // black box: the requirement has one obvious class, so 42 "seems enough"
    void smallNumberIsUnchanged() {
        assertEquals("42", pretty(42));
    }

    @Disabled("Planted bug: remove @Disabled in the lecture. Branch coverage forces a value over 1000")
    @Test // white box: coverage shows the "number > 1000" branch was never taken
    void fiveThousandIsShownInThousands() {
        assertEquals("5 thousand", pretty(5000));
    }
}
