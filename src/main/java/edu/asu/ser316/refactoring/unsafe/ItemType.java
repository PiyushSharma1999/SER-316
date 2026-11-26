package edu.asu.ser316.refactoring.unsafe;

enum ItemType {
    BOOK(0.25, 10.00),
    DVD(1.00, 10.00),
    LAPTOP(5.00, 50.00);

    private final double dailyRate;
    private final double maxFee;

    ItemType(double dailyRate, double maxFee) {
        this.dailyRate = dailyRate;
        this.maxFee = maxFee;
    }

    double feeFor(int daysLate) {
        return Math.min(daysLate * dailyRate, maxFee);
    }
}
