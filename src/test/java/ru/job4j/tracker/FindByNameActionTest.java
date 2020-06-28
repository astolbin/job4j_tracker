package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindByNameActionTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenItemsFound() {
        Tracker tracker = new Tracker();
        Item[] items = {
            new Item("Test 1"),
            new Item("Test 2"),
            new Item("Test 2"),
            new Item("Test 3")
        };
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);

        FindByNameAction action = new FindByNameAction();
        action.execute(new StubInput(new String[] {"Test 2"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
            .add("")
            .add("=== Find item by name ====")
            .add(items[1].toString())
            .add(items[2].toString())
            .add("")
            .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenItemsNotFound() {
        Tracker tracker = new Tracker();
        Item[] items = {
            new Item("Test 1"),
            new Item("Test 2"),
        };
        tracker.add(items[0]);
        tracker.add(items[1]);

        FindByNameAction action = new FindByNameAction();
        action.execute(new StubInput(new String[] {"Test 3"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
            .add("")
            .add("=== Find item by name ====")
            .add("")
            .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }
}