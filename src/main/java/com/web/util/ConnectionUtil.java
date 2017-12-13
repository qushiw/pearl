package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        Statement statement = null;
        try {

            String selectSql = "select * from account where userid = 4";
//            String strSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
//                    "values (4,'q','q','q','q','q','q','q','q','q','q','q')";

            Properties props = new Properties();
            props.setProperty("connectionAttributes", selectSql + ":true,cds-proxy:test");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_1?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true", props);//到底获得什么连接了??
            statement = connection.createStatement();

            ResultSet num = statement.executeQuery(selectSql);
            while (num.next()) {
                System.out.println(num.getString(2));

            }

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
