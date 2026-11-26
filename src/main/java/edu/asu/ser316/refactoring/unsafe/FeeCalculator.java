package edu.asu.ser316.refactoring.unsafe;

public class FeeCalculator {

    static final double MEMBER_DISCOUNT = 0.5;
    static final int GRACE_DAYS = 2;

    public double lateFee(String itemType, int daysLate, boolean member) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("days late cannot be negative");
        }
        if (daysLate <= GRACE_DAYS) {
            return 0;
        }
        double fee = ItemType.valueOf(itemType).feeFor(daysLate);
        return member ? fee * (1 - MEMBER_DISCOUNT) : fee;
    }
}
