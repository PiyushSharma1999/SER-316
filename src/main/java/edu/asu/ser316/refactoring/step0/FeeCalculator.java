package edu.asu.ser316.refactoring.step0;

public class FeeCalculator {

    // calculates the fee
    public double lateFee(String t, int d, boolean m) {
        if (d < 0) {
            throw new IllegalArgumentException("days late cannot be negative");
        }
        double f = 0;
        if (t.equals("BOOK")) {
            f = d * 0.25;
            if (f > 10.0) {
                f = 10.0;
            }
        } else if (t.equals("DVD")) {
            f = d * 1.00;
            if (f > 10.0) {
                f = 10.0;
            }
        } else if (t.equals("LAPTOP")) {
            f = d * 5.00;
            if (f > 50.0) {
                f = 50.0;
            }
        } else {
            throw new IllegalArgumentException("unknown item type: " + t);
        }
        if (m) {
            f = f - f * 0.5;
        }
        if (d <= 2) {
            f = 0;
        }
        return f;
    }
}
