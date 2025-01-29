/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.communitask2;

/**
 *
 * @author ADMIN
 */

import java.sql.*;

public class database {
    private static final String DATABASE_URL = "jdbc:sqlite:users.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {

            String createTableSQL = """
                                    CREATE TABLE IF NOT EXISTS users (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        first_name TEXT NOT NULL,
                                        middle_name TEXT,
                                        last_name TEXT NOT NULL,
                                        email TEXT NOT NULL UNIQUE,
                                        password TEXT NOT NULL
                                    );""";
            stmt.execute(createTableSQL);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
}

