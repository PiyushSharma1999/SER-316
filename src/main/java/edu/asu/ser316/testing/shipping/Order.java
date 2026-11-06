package edu.asu.ser316.testing.shipping;

/**
 * Requirement given to students (black box):
 * Orders over $50.00 ship free. Other orders pay $5.00 shipping.
 * A negative total is rejected with an IllegalArgumentException.
 *
 * The express rule below is NOT in the requirement. It was added later by
 * another developer. The black-box tests never reach it, which is the
 * starting point for the white-box module.
 */
public class Order {

    private final double total;
    private final boolean express;

    public Order(double total) {
        this(total, false);
    }

    public Order(double total, boolean express) {
        this.total = total;
        this.express = express;
    }

    public double getTotal() {
        return total;
    }

    public boolean isExpress() {
        return express;
    }

    public double shippingCost() {
        if (total < 0) {
            throw new IllegalArgumentException("Order total must not be negative");
        }
        if (total > 50.00) {
            return 0.00;
        }
        if (express) {
            return 12.00;
        }
        return 5.00;
    }
}
