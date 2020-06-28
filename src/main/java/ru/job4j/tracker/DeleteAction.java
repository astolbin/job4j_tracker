package ru.job4j.tracker;

public class DeleteAction implements UserAction {

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println();
        System.out.println("=== Delete Item by id ====");
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item with id '" + id + "' has been deleted.");
        } else {
            System.out.println("Item with id '" + id + "' not found.");
        }
        System.out.println();
        return true;
    }
}
