package it.unical;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    public enum DeliveryStatus {
        DELIVERED,
        NO_SUCH_ITEM,
        NOT_ENOUGH_STOCK
    }

    private final Map<String, Integer> items = new HashMap<>();

    public void addItem(String name, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }

        String normalized = name.toLowerCase().trim();
        items.merge(normalized, quantity, Integer::sum);
    }

    public DeliveryStatus deliver(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }

        String normalized = name.toLowerCase().trim();

        if (!items.containsKey(normalized)) {
            return DeliveryStatus.NO_SUCH_ITEM;
        }

        int available = items.get(normalized);

        if (available < quantity) {
            return DeliveryStatus.NOT_ENOUGH_STOCK;
        }

        items.put(normalized, available - quantity);

        if (items.get(normalized) == 0) {
            items.remove(normalized);
        }

        return DeliveryStatus.DELIVERED;
    }

    public int getQuantity(String name) {
        return items.getOrDefault(name.toLowerCase().trim(), 0);
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
