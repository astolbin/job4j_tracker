package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    private Store tracker;

    @Before
    public void initTracker() {
        tracker = new MemTracker();
    }

    @Test
    public void whenExit() {
        StubInput input = new StubInput(
            new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, tracker, new UserAction[] { action });
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
            new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, tracker, new UserAction[] { action });
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
            .add("Menu.")
            .add("0. Stub action")
            .toString();
        assertThat(out.toString(), is(expect));
        System.setOut(def);
    }
}