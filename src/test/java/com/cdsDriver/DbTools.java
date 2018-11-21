package com.cdsDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */
public class DbTools {

    private static String dirverClassName = "com.jdjr.cds.driver.CdsDriver";

    public static Connection makeConnection(String url) {
        Connection conn = null;
        try {
            Class.forName(dirverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection makeConnection(String url, Properties properties) {
        Connection conn = null;
        try {
            Class.forName(dirverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url,properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
