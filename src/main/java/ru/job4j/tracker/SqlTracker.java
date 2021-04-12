package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTracker implements Store {
    private final Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        String sql = "delete from items where id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsl;
    }

    @Override
    public Item add(Item item) {
        String sql = "insert into items (name) values (?)";
        try (PreparedStatement statement =
                     cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            ResultSet rsl = statement.getGeneratedKeys();
            if (rsl.next()) {
                item.setId(rsl.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        String sql = "update items set name = ? where id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        String sql = "select * from items";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            ResultSet rsl = statement.executeQuery();
            while (rsl.next()) {
                Item item = new Item(rsl.getString("name"));
                item.setId(rsl.getInt("id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        String sql = "select * from items where name = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, key);
            ResultSet rsl = statement.executeQuery();
            while (rsl.next()) {
                Item item = new Item(rsl.getString("name"));
                item.setId(rsl.getInt("id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        String sql = "select * from items where id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rsl = statement.executeQuery();
            if (rsl.next()) {
                item = new Item(rsl.getString("name"));
                item.setId(rsl.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
