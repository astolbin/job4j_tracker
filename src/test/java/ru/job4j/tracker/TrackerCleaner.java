package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class TrackerCleaner {
    public static void clear() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

            try (PreparedStatement statement = cn.prepareStatement("truncate table items")) {
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
