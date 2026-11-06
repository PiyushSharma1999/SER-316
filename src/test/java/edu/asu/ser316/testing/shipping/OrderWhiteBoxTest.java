package edu.asu.ser316.testing.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * White-box additions. Run the black-box tests with JaCoCo first:
 * the "if (express)" branch is never true. This test closes that gap.
 */
class OrderWhiteBoxTest {

    @Test
    void expressOrderUnderFiftyPaysTwelve() {
        Order order = new Order(20.00, true);
        assertEquals(12.00, order.shippingCost(), 0.001);
    }

    // Discussion: in the code, an express order over $50 ships free,
    // because the $50 check comes first. The requirement says nothing
    // about express. Is that intended? Coverage found the question,
    // only the requirement can answer it.
}
