package com.vidwel.zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
/*
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Stop43Cnjg";

    public static void main(String[] args) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS dbuser("
                + "USER_ID INT(5) NOT NULL, "
                + "USER_NAME VARCHAR(20) NOT NULL, "
                + "LAST_NAME VARCHAR(20) NOT NULL, "
                + "PRIMARY KEY (USER_ID) "
                + ")";

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = dbConnection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table \"dbuser\" is created!");
            statement.execute("insert into dbuser values (5,'Alex','Cherba')");
            statement.execute("UPDATE dbuser SET USER_NAME='Corny',LAST_NAME='Love' WHERE USER_ID=2");
            statement.execute("DELETE FROM my_db.")
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
*/
}
