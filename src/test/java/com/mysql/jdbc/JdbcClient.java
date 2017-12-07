package com.mysql.jdbc;


import java.sql.*;

/**
*
* @author qushiwen
* @create 2017-12-05
* @version 1.0
**/
public class JdbcClient {

    public static void main(String[] args) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://10.9.3.234:3307/jpetstore_dst?user=root&password=123456&useServerPrepStmts=true");
            preparedStatement = (PreparedStatement) connection.prepareStatement("update account set email = ? where userid = ?");

            for (int i=0; i<100; i++) {
                preparedStatement.setString(1, "qsfs" + i);
                preparedStatement.setString(2, ""+i);
                preparedStatement.addBatch();
            }
            int[] ints = preparedStatement.executeBatch();

            for (int i : ints) {
                System.out.println(ints[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
