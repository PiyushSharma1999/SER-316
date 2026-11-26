package edu.asu.ser316.refactoring.step3;

public class FeeCalculator {

    static final double BOOK_DAILY_RATE = 0.25;
    static final double BOOK_MAX_FEE = 10.00;
    static final double DVD_DAILY_RATE = 1.00;
    static final double DVD_MAX_FEE = 10.00;
    static final double LAPTOP_DAILY_RATE = 5.00;
    static final double LAPTOP_MAX_FEE = 50.00;
    static final double MEMBER_DISCOUNT = 0.5;
    static final int GRACE_DAYS = 2;

    public double lateFee(String itemType, int daysLate, boolean member) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("days late cannot be negative");
        }
        double fee;
        if (itemType.equals("BOOK")) {
            fee = cappedFee(daysLate, BOOK_DAILY_RATE, BOOK_MAX_FEE);
        } else if (itemType.equals("DVD")) {
            fee = cappedFee(daysLate, DVD_DAILY_RATE, DVD_MAX_FEE);
        } else if (itemType.equals("LAPTOP")) {
            fee = cappedFee(daysLate, LAPTOP_DAILY_RATE, LAPTOP_MAX_FEE);
        } else {
            throw new IllegalArgumentException("unknown item type: " + itemType);
        }
        if (member) {
            fee = fee - fee * MEMBER_DISCOUNT;
        }
        if (daysLate <= GRACE_DAYS) {
            fee = 0;
        }
        return fee;
    }

    private double cappedFee(int daysLate, double dailyRate, double maxFee) {
        return Math.min(daysLate * dailyRate, maxFee);
    }
}
