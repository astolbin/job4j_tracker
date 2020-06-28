package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;

    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    public Item[] findByName(String key) {
        Item[] itemsFound = new Item[position];
        int size = 0;

        for (int i = 0; i < position; i++) {
            Item item = items[i];
            if (item.getName().equals(key)) {
                itemsFound[size] = item;
                size++;
            }
        }

        return Arrays.copyOf(itemsFound, size);
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(String id, Item itemForReplace) {
        int index = indexOf(id);
        boolean result = false;
        if (index >= 0 && itemForReplace != null) {
            itemForReplace.setId(items[index].getId());
            items[index] = itemForReplace;
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index >= 0) {
            int start = index + 1;
            int size = position - index;
            System.arraycopy(items, start, items, index, size);
            items[position - 1] = null;
            position--;
            result = true;
        }
        return result;
    }
}