package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateInputTest {
    private final PrintStream out = System.out;
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void backOutput() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
            new StubInput(new String[] {"one", "1"})
        );
        input.askInt("Enter");
        assertThat(
            mem.toString(),
            is(String.format("Please enter validate data again.%n"))
        );
    }

    @Test
    public void whenInvalidMenuNumber() {
        ValidateInput input = new ValidateInput(
            new StubInput(new String[] {"2", "1"})
        );
        input.askInt("Enter", 2);
        assertThat(
            mem.toString(),
            is(String.format("Please select key from menu.%n"))
        );
    }
}