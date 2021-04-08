package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShowAllActionTest {
    private Store tracker;

    @Before
    public void initTracker() throws SQLException {
        tracker = new SqlTracker(
                ConnectionRollback.create(
                        ConnectionGenerator.get("app.properties")
                )
        );
    }

    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
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