package edu.asu.ser316.testing.enrollment;

public class Student {

    private final String name;
    private int credits;

    public Student(String name, int credits) {
        if (credits < 0) {
            throw new IllegalArgumentException("Credits must not be negative");
        }
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    void addCredits(int amount) {
        credits += amount;
    }

    @Override
    public String toString() {
        return name + " (" + credits + " credits)";
    }
}
