package com.web.util;

import java.sql.*;

/**
*
* @author qushiwen
* @create 2017-12-06
* @version 1.0
**/
public class ConnectionUtil {


    private void prepareBatchExecute() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_1?user=qsw_ha_app_test" +
                    "&password=123456&useServerPrepStmts=true&rewriteBatchedStatements=true");

//            String strSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) " +
//                    "values (?,'q',null,'q',null,'q',null,'q','q','q','q','q')";

            String strSql = "insert into a(name2, name1, name, time_stamp_demo, time_demo, data_time_demo, date_demo, id) " +
                    "values(null, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
//            connection.setAutoCommit(false);
            for (int i=30; i<40; i++) {
//                preparedStatement.setString(1, null);
                preparedStatement.setString(1, "q");
                preparedStatement.setString(2, "q");
                java.util.Date date = new java.util.Date();
                preparedStatement.setTimestamp(3, new Timestamp(date.getTime()));
                preparedStatement.setTime(4, new Time(date.getTime()));
                preparedStatement.setDate(5, new Date(date.getTime()));
                preparedStatement.setDate(6, new Date(date.getTime()));
                preparedStatement.setInt(7 , i);
                preparedStatement.addBatch();
            }
            int[] nums = preparedStatement.executeBatch();
            System.out.println("size : " + nums.length);
            // 1
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            //2
            String selectLastKey = "SELECT LAST_INSERT_ID()";
            ResultSet resultSet = preparedStatement.executeQuery(selectLastKey);

            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
//            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void commonExecute() {
        try {
            String selectSql = "SELECT userid FROM account where email = 'q' group by userid desc";

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_2?user=qsw_ha_app_test&password=123456");
            String strSql = "insert into a(name2) values(76, 'q')";
//            Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.3.234:3309/jpetstore_dst?user=root&password=123456&useCursorFetch=true");
//            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(strSql,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
//            statement.setQueryTimeout(20);
//            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("value : " + resultSet.getString(1));
            }
//            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void searchAutoKeyTest(){
        String lastInsertSql = "SELECT LAST_INSERT_ID()";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_2?user=qsw_ha_app_test&password=123456");
            Statement statement = connection.createStatement();
            String insertSql = "insert into a(id, name2, name1, name) values(500, 'q', 'q', 'q')";
            String updateSql = "update a set name2 = 'name2' where id = 275";
            String insertAccountSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone) values (?,'q','q','q','q','q','q','q','q','q','q','q')";


            statement.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = statement.getGeneratedKeys();

            ResultSet resultSet = statement.executeQuery(lastInsertSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        }catch ( Exception e) {
            e.printStackTrace();
        }
    }



    private void mysqlPrepareExecute() {
        try {
            String selectSql = "SELECT supplier, status FROM item WHERE itemid = ?";
            String insertSql = "insert into item(itemid, productid, listprice, unitcost, supplier, status, attr1, attr2, attr3, attr4, attr5) " +
                    "values (?,'q','1','1','1','q','q','q','q','q','q')";
            Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.3.234:3309/jpetstore_dst?user=root&password=123456&useServerPrepStmts=true");
//            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
//            preparedStatement.setFetchSize(3);
            preparedStatement.setString(1, "999");
            int resultSet = preparedStatement.executeUpdate();
//            while (resultSet.next()) {
//                System.out.println("value : " + resultSet.getString("supplier") + "," + resultSet.getString("status"));
//            }
//            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void prepareExecute() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8307/qsw_test_2?user=qsw_ha_app_test&password=123456&useServerPrepStmts=true");
//            String insertSql = "insert into account(userid, email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone)" +
//                    "                    values (?,'q','q','q','q','q','q','q','q','q','q','q')";
            String selectSql = "select userid from account where email = ?  order by userid";

            String insertSql = "insert into item(itemid, productid, listprice, unitcost, supplier, status, attr1, attr2, attr3, attr4, attr5) " +
                    "values (?,'q','1','1','1','q','q','q','q','q','q')";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "q");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("the result data:" + resultSet.getString(1));
            }
            System.out.println("---------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.searchAutoKeyTest();
    }
}
