package edu.asu.ser316.testing.store;

public class Order {

    private final String item;
    private final int quantity;

    Order(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
