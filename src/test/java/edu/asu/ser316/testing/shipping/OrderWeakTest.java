package edu.asu.ser316.testing.shipping;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Intentionally WEAK tests from the slides. They pass, but they would also
 * pass if the code were wrong. Use them to discuss weak assertions and
 * mutation testing: change "return 5.00" to "return 0.00" and these still pass.
 */
class OrderWeakTest {

    private static Order orderWithTotal(double total) {
        return new Order(total);
    }

    @Test // weak: passes for $0 and $5, so broken free shipping goes unnoticed
    void testShipping() {
        Order order = orderWithTotal(60.00);
        double s = order.shippingCost();
        assertTrue(s >= 0);
    }

    @Test // weak: reaches the code (coverage goes up) but checks nothing
    void coverSmallOrder() {
        orderWithTotal(20.00).shippingCost();
    }
}
