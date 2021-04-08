package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TrackerTest{
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
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("Test 1");
        tracker.add(item);

        Item result = tracker.findById(Integer.parseInt(item.getId()));
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTrackerNotFoundItemById() {
        Item result = tracker.findById(-1);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenFindAllThanReturnAllItems() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        List<Item> items = tracker.findAll();
        assertThat(items.size(), is(3));
    }

    @Test
    public void whenFindItemsByNameThan2ItemsFound() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        List<Item> items = tracker.findByName("Test 2");
        assertThat(items.size(), is(2));
    }

    @Test
    public void whenFindItemsByNameThanNoItemsFound() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        List<Item> items = tracker.findByName("Test 4");
        assertThat(items.size(), is(0));
    }

    @Test
    public void whenReplace() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = Integer.parseInt(bug.getId());
        Item bugWithDesc = new Item("Bug with description");
        boolean result = tracker.replace(id, bugWithDesc);
        assertThat(result, is(true));
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenReplaceNotFound() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = Integer.parseInt(bug.getId());
        Item bugWithDesc = new Item("Bug with description");
        boolean result = tracker.replace(-1, bugWithDesc);
        assertThat(result, is(false));
        assertThat(tracker.findById(id).getName(), is("Bug"));
    }

    @Test
    public void whenDelete() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = Integer.parseInt(bug.getId());
        boolean result = tracker.delete(id);

        assertThat(result, is(true));
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteNotFound() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = Integer.parseInt(bug.getId());
        boolean result = tracker.delete(-1);

        assertThat(result, is(false));
        assertThat(tracker.findById(id), is(bug));
    }
}