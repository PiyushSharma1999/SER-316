package edu.asu.ser316.refactoring.step1;

public class FeeCalculator {

    public double lateFee(String itemType, int daysLate, boolean member) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("days late cannot be negative");
        }
        double fee = 0;
        if (itemType.equals("BOOK")) {
            fee = daysLate * 0.25;
            if (fee > 10.0) {
                fee = 10.0;
            }
        } else if (itemType.equals("DVD")) {
            fee = daysLate * 1.00;
            if (fee > 10.0) {
                fee = 10.0;
            }
        } else if (itemType.equals("LAPTOP")) {
            fee = daysLate * 5.00;
            if (fee > 50.0) {
                fee = 50.0;
            }
        } else {
            throw new IllegalArgumentException("unknown item type: " + itemType);
        }
        if (member) {
            fee = fee - fee * 0.5;
        }
        if (daysLate <= 2) {
            fee = 0;
        }
        return fee;
    }
}
