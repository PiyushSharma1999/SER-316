package edu.asu.ser316.testing.enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {

    private final String name;
    private final int credits;
    private final int capacity;
    private final List<Student> roster = new ArrayList<>();

    public Course(String name, int credits, int capacity) {
        if (credits <= 0 || capacity <= 0) {
            throw new IllegalArgumentException("Credits and capacity must be positive");
        }
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return roster.size() >= capacity;
    }

    /** Read-only view of the students in the course. */
    public List<Student> getRoster() {
        return Collections.unmodifiableList(roster);
    }

    void add(Student student) {
        roster.add(student);
    }
}
