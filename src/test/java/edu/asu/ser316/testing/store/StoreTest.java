package edu.asu.ser316.testing.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests check state, not only return values.
 */
class StoreTest {

    private Inventory inventory;
    private Store store;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        store = new Store(inventory);
    }

    @Test
    void placingOrderUpdatesHistoryAndStock() {
        // Arrange: inputs and starting state
        Customer c = new Customer("Ana");
        inventory.setStock("mug", 5);
        // Act
        Order o = store.placeOrder(c, "mug", 2);
        // Assert: output and new state
        assertEquals(2, o.getQuantity());
        assertEquals(1, c.getOrders().size());
        assertEquals(3, inventory.getStock("mug"));
    }

    @Test
    void notEnoughStockChangesNothing() {
        Customer c = new Customer("Ana");
        inventory.setStock("mug", 1);

        assertThrows(IllegalStateException.class, () -> store.placeOrder(c, "mug", 2));
        assertEquals(0, c.getOrders().size());
        assertEquals(1, inventory.getStock("mug"));
    }
}
