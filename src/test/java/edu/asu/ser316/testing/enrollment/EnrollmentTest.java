package edu.asu.ser316.testing.enrollment;

import static edu.asu.ser316.testing.enrollment.EnrollResult.ALREADY_ENROLLED;
import static edu.asu.ser316.testing.enrollment.EnrollResult.COURSE_FULL;
import static edu.asu.ser316.testing.enrollment.EnrollResult.CREDIT_LIMIT;
import static edu.asu.ser316.testing.enrollment.EnrollResult.ENROLLED;
import static edu.asu.ser316.testing.enrollment.Enrollment.enroll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Black-box tests for enroll(). Most inputs come from the starting state
 * (seats taken, credits carried, current roster), not from the arguments.
 * Every test checks the result AND the state.
 */
class EnrollmentTest {

    /** Fills the course with other students until it holds count students. */
    private static void fillRoster(Course c, int count) {
        for (int i = 0; i < count; i++) {
            enroll(new Student("Other" + i, 0), c);
        }
    }

    // --- Open seats (state of the course) ---

    @Test // boundary: last open seat
    void lastOpenSeatCanBeTaken() {
        Course c = new Course("SER316", 3, 30);
        fillRoster(c, 29);
        Student s = new Student("Ana", 12);

        assertEquals(ENROLLED, enroll(s, c));
        assertTrue(c.getRoster().contains(s));
        assertEquals(15, s.getCredits());
    }

    @Test // boundary: no seats left
    void fullCourseIsRejectedAndNothingChanges() {
        Course c = new Course("SER316", 3, 30);
        fillRoster(c, 30);
        Student s = new Student("Ana", 12);

        assertEquals(COURSE_FULL, enroll(s, c));
        assertFalse(c.getRoster().contains(s));
        assertEquals(30, c.getRoster().size());
        assertEquals(12, s.getCredits());
    }

    // --- Already enrolled (state of the roster, not a number) ---

    @Test
    void enrollingTwiceIsRejectedAndNothingChanges() {
        Course c = new Course("SER316", 3, 30);
        Student s = new Student("Ana", 12);
        enroll(s, c);

        assertEquals(ALREADY_ENROLLED, enroll(s, c));
        assertEquals(1, c.getRoster().size());
        assertEquals(15, s.getCredits());
    }

    // --- Credits after enrolling (state + input) ---

    @Test // boundary: 15 + 3 = 18 is allowed
    void reachingExactlyEighteenIsAllowed() {
        Course c = new Course("SER316", 3, 30);
        Student s = new Student("Ana", 15);

        assertEquals(ENROLLED, enroll(s, c));
        assertEquals(18, s.getCredits());
    }

    @Test // boundary: 16 + 3 = 19 is over the limit
    void goingOverEighteenIsRejected() {
        Course c = new Course("SER316", 3, 30);
        Student s = new Student("Ben", 16);

        assertEquals(CREDIT_LIMIT, enroll(s, c));
        // failure changes nothing
        assertFalse(c.getRoster().contains(s));
        assertEquals(16, s.getCredits());
    }

    // --- Invalid arguments ---

    @Test
    void nullStudentIsRejected() {
        Course c = new Course("SER316", 3, 30);
        assertThrows(IllegalArgumentException.class, () -> enroll(null, c));
    }

    @Test
    void nullCourseIsRejected() {
        Student s = new Student("Ana", 12);
        assertThrows(IllegalArgumentException.class, () -> enroll(s, null));
    }
}
