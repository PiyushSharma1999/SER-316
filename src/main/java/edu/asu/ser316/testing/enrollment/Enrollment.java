package edu.asu.ser316.testing.enrollment;

/**
 * Requirement:
 * A student can enroll if the course has an open seat and the student is not
 * already in it. A student may carry at most 18 credits, and enrolling must
 * not take them over 18.
 * Returns ENROLLED, COURSE_FULL, ALREADY_ENROLLED or CREDIT_LIMIT.
 * A null student or course throws IllegalArgumentException.
 * On success the student joins the roster and their credits go up by the
 * course credits. On any failure nothing changes.
 */
public final class Enrollment {

    public static final int MAX_CREDITS = 18;

    private Enrollment() {
    }

    public static EnrollResult enroll(Student s, Course c) {
        if (s == null || c == null) {
            throw new IllegalArgumentException("Student and course are required");
        }
        if (c.isFull()) {
            return EnrollResult.COURSE_FULL;
        }
        if (c.getRoster().contains(s)) {
            return EnrollResult.ALREADY_ENROLLED;
        }
        if (s.getCredits() + c.getCredits() > MAX_CREDITS) {
            return EnrollResult.CREDIT_LIMIT;
        }
        c.add(s);
        s.addCredits(c.getCredits());
        return EnrollResult.ENROLLED;
    }
}
