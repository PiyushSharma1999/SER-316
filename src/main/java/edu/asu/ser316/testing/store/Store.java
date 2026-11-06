package edu.asu.ser316.testing.store;

/**
 * Requirement: placing an order adds it to the customer's order history and
 * lowers the stock of the item by the quantity ordered. If there is not enough
 * stock, an IllegalStateException is thrown and nothing changes.
 */
public class Store {

    private final Inventory inventory;

    public Store(Inventory inventory) {
        this.inventory = inventory;
    }

    public Order placeOrder(Customer customer, String item, int quantity) {
        if (customer == null || item == null || quantity <= 0) {
            throw new IllegalArgumentException("Customer, item and a positive quantity are required");
        }
        int available = inventory.getStock(item);
        if (available < quantity) {
            throw new IllegalStateException("Not enough stock for " + item);
        }
        Order order = new Order(item, quantity);
        inventory.setStock(item, available - quantity);
        customer.addOrder(order);
        return order;
    }
}
