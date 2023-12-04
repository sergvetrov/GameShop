package org.example.configuration;

import org.example.enums.RepoSystemMessages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "qwerty";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/game_shop", name, password);
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_CREATE_CONNECTION);
        }
        return null;
    }
}
