package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";

        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "password");

        Connection connection = DriverManager.getConnection(url, properties);

        Statement statement = connection.createStatement();

        String query = "SELECT * FROM people";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String name = resultSet.getString("name");

            System.out.println(name);
        }
    }
}
