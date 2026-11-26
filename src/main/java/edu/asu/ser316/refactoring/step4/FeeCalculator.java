package edu.asu.ser316.refactoring.step4;

public class FeeCalculator {

    static final double MEMBER_DISCOUNT = 0.5;
    static final int GRACE_DAYS = 2;

    public double lateFee(String itemType, int daysLate, boolean member) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("days late cannot be negative");
        }
        double fee = ItemType.valueOf(itemType).feeFor(daysLate);
        if (member) {
            fee = fee - fee * MEMBER_DISCOUNT;
        }
        if (daysLate <= GRACE_DAYS) {
            fee = 0;
        }
        return fee;
    }
}
