package edu.asu.ser316.testing.parity;

import static edu.asu.ser316.testing.parity.Parity.evenOrOdd;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * When black box catches what white box misses.
 */
class ParityTest {

    @Test // white box: one test runs every line, 100% coverage, and passes
    void fourIsEven() {
        assertEquals(0, evenOrOdd(4));
    }

    @Disabled("Planted bug: remove @Disabled in the lecture. The spec has an odd class, so 3 must return -1")
    @Test // black box: the odd partition from the requirement
    void threeIsOdd() {
        assertEquals(-1, evenOrOdd(3));
    }
}
