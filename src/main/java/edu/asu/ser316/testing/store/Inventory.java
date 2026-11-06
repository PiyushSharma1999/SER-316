package edu.asu.ser316.testing.store;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Integer> stock = new HashMap<>();

    public void setStock(String item, int count) {
        stock.put(item, count);
    }

    public int getStock(String item) {
        return stock.getOrDefault(item, 0);
    }
}
