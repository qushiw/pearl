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
        Statement statement = null;
        PreparedStatement preparedStatement;
        try {

//            String strSql = "select * from account";
//            String strSql = "insert into account(email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
//                    "values ('q','q','q','q','q','q','q','q','q','q','q')";
//            String strSql = "insert into lineitem(linenum, itemid, quantity, unitprice) values(1, '123', 11, 12.1)";
            String strSql = "update account set email = '666' where userid in ('0','6')";
//            String strSql = "SELECT increment_step FROM cluster_autoinc_table WHERE table_name=? AND column_name=?";

            Properties props = new Properties();
            props.setProperty("connectionAttributes", strSql + ":true,cds-proxy:test");
//            connection = DriverManager.getConnection("jdbc:mysql://10.9.3.234:3309/test?user=root&password=123456&useServerPrepStmts=true");//到底获得什么连接了??
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_5?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true", props);//到底获得什么连接了??


//            preparedStatement = connection.prepareStatement(strSql);
//            preparedStatement.setString(1, "lineitem");
//            preparedStatement.setString(2, "orderid");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//            }

            connection.setAutoCommit(false);
            statement = connection.createStatement();
            int resultSet = statement.executeUpdate(strSql);
            System.out.println(resultSet);

            connection.commit();
            System.out.println(resultSet);

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


            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("完美退出");
                }
            }));
        }
    }

}
