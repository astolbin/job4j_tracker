package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShowAllActionTest {
    @Before
    public void clearItems() {
        TrackerCleaner.clear();
    }

    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("fix bug");
        tracker.add(item);
        ShowAllAction act = new ShowAllAction();
        act.execute(new StubInput(new String[] {}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
            .add("")
            .add("=== Show all items ====")
            .add(item.toString())
            .add("")
            .toString();
        assertThat(out.toString(), is(expect));
        System.setOut(def);
    }
}