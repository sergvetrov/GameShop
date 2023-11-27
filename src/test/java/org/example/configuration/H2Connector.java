package org.example.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connector {


    public static Connection get() throws SQLException, IOException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        dbInit(connection);
        return connection;
    }

    private static void dbInit(Connection connection) throws SQLException, IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/init.sql")));
        Statement statement = connection.createStatement();
        statement.execute(content);
        statement.close();
    }
}
