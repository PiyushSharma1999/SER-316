package edu.asu.ser316.testing.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Black-box tests, designed from the requirement only.
 * Requirement: orders over $50.00 ship free, other orders pay $5.00,
 * a negative total is rejected.
 */
class OrderBlackBoxTest {

    private static Order orderWithTotal(double total) {
        return new Order(total);
    }

    @Test // T1: typical free order
    void ordersOverFiftyShipFree() {
        assertEquals(0.00, orderWithTotal(60.00).shippingCost(), 0.001);
    }

    @Test // T2: typical paid order
    void smallOrdersPayFive() {
        assertEquals(5.00, orderWithTotal(20.00).shippingCost(), 0.001);
    }

    @Test // T3: boundary, "over" excludes 50.00
    void exactlyFiftyPaysFive() {
        assertEquals(5.00, orderWithTotal(50.00).shippingCost(), 0.001);
    }

    @Test // T4: boundary, first free value
    void fiftyAndOneCentShipsFree() {
        assertEquals(0.00, orderWithTotal(50.01).shippingCost(), 0.001);
    }

    @Test // T5: invalid partition
    void negativeTotalIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> orderWithTotal(-1.00).shippingCost());
    }
}
