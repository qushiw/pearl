package com.web.util;

import java.sql.*;
import java.util.Properties;

/**
*
* @author qushiwen
* @create 2017-12-06
* @version 1.0
**/
public class ConnectionUtil {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String strSql = "update account set email = ?,a=?  where userid = ?";
//            String strSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
//                    "values (13,'q','q','q','q','q','q','q','q','q','q','q')";
            Properties props = new Properties();
            props.setProperty("connectionAttributes", strSql + ":true,cds-proxy:test");
            connection = DriverManager.getConnection("jdbc:mysql://10.9.3.234:3307/jpetstore_dst?user=root&password=123456&useServerPrepStmts=true");
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_1?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true", props);
            statement = connection.prepareStatement(strSql);
            statement.setString(1, "qsfs");
            statement.setInt(2, 1);
            statement.setString(3, "1");
            int changeNum = statement.executeUpdate();
            System.out.println("--------------------------------------------" + changeNum);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (statement != null) {
                    statement.close();

                }
            } catch (Exception e) {

            }
        }
    }

}
