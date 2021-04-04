package ru.job4j.tracker;

public class ReplaceAction implements UserAction {

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println();
        System.out.println("=== Edit the Item ====");
        int id = Integer.parseInt(input.askStr("Enter id: "));
        Item item = new Item(input.askStr("Enter name: "));
        if (tracker.replace(id, item)) {
            System.out.println("Item with id '" + id + "' has been replaced.");
        } else {
            System.out.println("Item with id '" + id + "' not found.");
        }
        System.out.println();
        return true;
    }
}
