package ru.job4j.tracker;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class SqlTrackerTest {
    public SqlTracker getTracker() throws SQLException {
        return new SqlTracker(
                ConnectionRollback.create(
                        ConnectionGenerator.get("app.properties")
                )
        );
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item item = new Item("Test 1");
            tracker.add(item);

            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
            assertEquals(tracker.findByName("Test 1").size(), 1);
        }
    }

    @Test
    public void whenTrackerNotFoundItemById() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item result = tracker.findById(-1);
            assertThat(result, is(nullValue()));
        }
    }

    @Test
    public void whenFindAllThanReturnAllItems() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            tracker.add(new Item("Test 1"));
            tracker.add(new Item("Test 2"));
            tracker.add(new Item("Test 3"));

            List<Item> items = tracker.findAll();
            assertThat(items.size(), is(3));
            assertEquals(tracker.findByName("Test 1").size(), 1);
        }
    }

    @Test
    public void whenFindItemsByNameThan2ItemsFound() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            tracker.add(new Item("Test 1"));
            tracker.add(new Item("Test 2"));
            tracker.add(new Item("Test 2"));
            tracker.add(new Item("Test 3"));

            List<Item> items = tracker.findByName("Test 2");
            assertThat(items.size(), is(2));
            assertEquals(tracker.findByName("Test 1").size(), 1);
            assertEquals(tracker.findByName("Test 2").size(), 2);
        }
    }

    @Test
    public void whenFindItemsByNameThanNoItemsFound() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            tracker.add(new Item("Test 1"));
            tracker.add(new Item("Test 2"));
            tracker.add(new Item("Test 2"));
            tracker.add(new Item("Test 3"));

            List<Item> items = tracker.findByName("Test 4");
            assertThat(items.size(), is(0));
            assertEquals(tracker.findByName("Test 2").size(), 2);
        }
    }

    @Test
    public void whenReplace() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item("Bug with description");
            boolean result = tracker.replace(id, bugWithDesc);
            assertThat(result, is(true));
            assertThat(tracker.findById(id).getName(), is("Bug with description"));
            assertEquals(tracker.findByName("Bug").size(), 0);
        }
    }

    @Test
    public void whenReplaceNotFound() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item("Bug with description");
            boolean result = tracker.replace(-1, bugWithDesc);
            assertThat(result, is(false));
            assertThat(tracker.findById(id).getName(), is("Bug"));
        }
    }

    @Test
    public void whenDelete() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            boolean result = tracker.delete(id);

            assertThat(result, is(true));
            assertThat(tracker.findById(id), is(nullValue()));
        }
    }

    @Test
    public void whenDeleteNotFound() throws Exception {
        try (SqlTracker tracker = getTracker()) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            boolean result = tracker.delete(-1);

            assertThat(result, is(false));
            assertThat(tracker.findById(id), is(bug));
        }
    }
}