package ru.job4j.tracker;

import java.util.Collections;
import java.util.List;

public class ItemSorter {
    public void sortByNameAsc(List<Item> items) {
        Collections.sort(items, new SortItemByNameAsc());
    }

    public void sortByNameDesc(List<Item> items) {
        Collections.sort(items, new SortItemByNameDesc());
    }
}
