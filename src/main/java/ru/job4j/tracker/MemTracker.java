package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();

    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    private int generateId() {
        Random rm = new Random();
        return (int) (rm.nextLong() + System.currentTimeMillis());
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemsFound = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().equals(key)) {
                itemsFound.add(item);
            }
        }

        return itemsFound;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            if (item.getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item itemForReplace) {
        int index = indexOf(id);
        boolean result = false;
        if (index >= 0 && itemForReplace != null) {
            itemForReplace.setId(items.get(index).getId());
            items.set(index, itemForReplace);
            result = true;
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        int index = indexOf(id);
        if (index >= 0) {
            items.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public void close() {

    }
}