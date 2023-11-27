package org.example.configuration;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSingletonTest {

    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        connection.close();
    }
}