package edu.asu.ser316.testing.grades;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * White-box tests for the Grades examples from the slides.
 * Tests marked @Disabled expose the planted bugs. Remove @Disabled
 * during the lecture to watch them fail.
 */
class GradesWhiteBoxTest {

    private final Grades grades = new Grades();

    // --- homeworkAverage: coverage criteria ---

    @Test // statement coverage: reaches every node, misses edge C -> E
    void statementCoverage() {
        assertEquals(80.0f, grades.homeworkAverage(new float[] {80, 70}), 0.001f);
    }

    @Test // branch coverage: 70 sets a new minimum, 80 does not
    void branchCoverage() {
        assertEquals(80.0f, grades.homeworkAverage(new float[] {70, 80}), 0.001f);
    }

    // --- homeworkAverage: loop coverage finds two bugs ---

    @Disabled("Planted bug: remove @Disabled in the lecture. Loop runs zero times and returns 99999.0")
    @Test
    void emptyScoresAreRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> grades.homeworkAverage(new float[] {}));
    }

    @Disabled("Planted bug: remove @Disabled in the lecture. Loop runs once and returns NaN (0 / 0)")
    @Test
    void singleScoreIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> grades.homeworkAverage(new float[] {85}));
    }

    // --- findAverage: activity answer ---

    @Test // statement coverage: 1 sets min, 2 sets min2, 3 changes neither, output runs
    void threeScoresReturnAverageAndOutputTwoLowest() {
        assertEquals(2.0f, grades.findAverage(new float[] {1, 2, 3}), 0.001f);
        assertArrayEquals(new float[] {1, 2}, grades.getLastLowestTwo(), 0.001f);
    }

    @Test // branch coverage: one score, so min2 stays MAX and output is skipped
    void singleScoreReturnsItselfWithNoOutput() {
        assertEquals(5.0f, grades.findAverage(new float[] {5}), 0.001f);
        assertNull(grades.getLastLowestTwo());
    }

    // Loop zero times: findAverage(new float[] {}) returns NaN.
    // The requirement does not say what should happen. This is a white-box
    // finding that sends you back to the requirement, so there is no test yet.
}
