package ru.job4j.tracker;

public class StartUI {
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        SqlTracker tracker = new SqlTracker(
                ConnectionGenerator.get("app.properties")
        );
        UserAction[] actions = {
            new CreateAction(),
            new ShowAllAction(),
            new ReplaceAction(),
            new DeleteAction(),
            new FindByIdAction(),
            new FindByNameAction(),
            new ExitAction()
        };
        new StartUI().init(input, tracker, actions);
    }

    public void init(Input input, Store tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }
}
