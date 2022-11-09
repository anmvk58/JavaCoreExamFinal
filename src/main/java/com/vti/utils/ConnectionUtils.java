package com.vti.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private Connection connection;

    public ConnectionUtils() {
        this.connection = null;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){

            // Khai báo thông tin kết nối tới Database
            String urlDb = "jdbc:mysql://localhost:3306/java";
            String username = "root";
            String password = "root";

            Connection connection = DriverManager.getConnection(urlDb, username, password);
            this.connection = connection;

            return connection;
        }
        return this.connection;
    }

    public void dropConnection() throws SQLException {
        if(this.connection != null || !this.connection.isClosed()){
            this.connection.close();
        }
    }
}
