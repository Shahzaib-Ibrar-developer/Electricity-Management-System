package com.electricity.manage;

import java.sql.Connection;
import java.sql.DriverManager;

public class CP {
    static Connection con;

    public static Connection createConnection() {
        // 5 Steps to create connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create the connection
            String user = "root";
            String password = "$Shah12345";
            String url = "jdbc:mysql://localhost:3306/bill_manage";
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return con;

    }
}
