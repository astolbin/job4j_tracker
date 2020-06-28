package ru.job4j.tracker;

import junit.framework.TestCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TrackerTest extends TestCase {
    private Tracker tracker;

    @Override
    public void setUp() {
        tracker = new Tracker();
    }

    public void testWhenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("Test 1");
        tracker.add(item);

        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    public void testWhenTrackerNotFoundItemById() {
        Item result = tracker.findById("Test 2");
        assertThat(result, is(nullValue()));
    }

    public void testWhenFindAllThanReturnAllItems() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        Item[] items = tracker.findAll();
        assertThat(items.length, is(3));
    }

    public void testWhenFindItemsByNameThan2ItemsFound() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        Item[] items = tracker.findByName("Test 2");
        assertThat(items.length, is(2));
    }

    public void testWhenFindItemsByNameThanNoItemsFound() {
        tracker.add(new Item("Test 1"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 2"));
        tracker.add(new Item("Test 3"));

        Item[] items = tracker.findByName("Test 4");
        assertThat(items.length, is(0));
    }

    public void testWhenReplace() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        boolean result = tracker.replace(id, bugWithDesc);
        assertThat(result, is(true));
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    public void testWhenReplaceNotFound() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        boolean result = tracker.replace("123", bugWithDesc);
        assertThat(result, is(false));
        assertThat(tracker.findById(id).getName(), is("Bug"));
    }

    public void testWhenDelete() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        boolean result = tracker.delete(id);

        assertThat(result, is(true));
        assertThat(tracker.findById(id), is(nullValue()));
    }

    public void testWhenDeleteNotFound() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        boolean result = tracker.delete("123");

        assertThat(result, is(false));
        assertThat(tracker.findById(id), is(bug));
    }
}