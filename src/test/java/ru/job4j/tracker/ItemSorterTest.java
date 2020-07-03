package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemSorterTest {
    @Test
    public void whenItemsSortByNameAsc() {
        List<Item> items = Arrays.asList(
                new Item("Test 3"),
                new Item("Test 1"),
                new Item("Test 2")
        );

        ItemSorter itemSorter = new ItemSorter();
        itemSorter.sortByNameAsc(items);

        assertThat(items.get(0).getName(), is("Test 1"));
    }

    @Test
    public void whenItemsSortByNameDesc() {
        List<Item> items = Arrays.asList(
                new Item("Test 2"),
                new Item("Test 1"),
                new Item("Test 3")
        );

        ItemSorter itemSorter = new ItemSorter();
        itemSorter.sortByNameDesc(items);

        assertThat(items.get(0).getName(), is("Test 3"));
    }
}