package edu.asu.ser316.testing.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

    private final String name;
    private final List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Read-only view of the customer's order history. */
    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    void addOrder(Order order) {
        orders.add(order);
    }
}
