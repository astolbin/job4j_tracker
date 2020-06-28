package ru.job4j.tracker;

public class FindByNameAction implements UserAction {

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println();
        System.out.println("=== Find item by name ====");
        Item[] items = tracker.findByName(input.askStr("Enter name: "));
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
        return true;
    }
}
