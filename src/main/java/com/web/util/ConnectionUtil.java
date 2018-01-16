package com.web.util;

import java.sql.*;
import java.util.Properties;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/**
*
* @author qushiwen
* @create 2017-12-06
* @version 1.0
**/
public class ConnectionUtil {

    public void prepareExecute() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_5?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true");//到底获得什么连接了??

            String updateSql = "select * from account where userid = ?";
//            String strSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
//                    "values (?,'q','q','q','q','q','q','q','q','q','q','q')";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, ""+10486);
            ResultSet re = preparedStatement.executeQuery();
            System.out.println("num : " + re);
        } catch (Exception e) {

        }

    }


    private void prepareBatchExecute() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_5?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true");//到底获得什么连接了??

            String strSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
                    "values (?,'q','q','q','q','q','q','q','q','q','q','q')";
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
            connection.setAutoCommit(false);
            for (int i=20046; i<20047; i++) {
                preparedStatement.setString(1, ""+i);
                preparedStatement.addBatch();
            }
            int[] nums = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("size : " + nums.length);
        } catch (Exception e) {

        }

    }


    public static void main(String[] args) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.prepareExecute();
    }

}
