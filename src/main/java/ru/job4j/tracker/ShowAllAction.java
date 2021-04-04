package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println();
        System.out.println("=== Show all items ====");
        List<Item> items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
        return true;
    }
}
