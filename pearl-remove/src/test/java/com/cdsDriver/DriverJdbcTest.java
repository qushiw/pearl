package com.cdsDriver;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */

//@RunWith(AiPerfRunner.class)
public class DriverJdbcTest {

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("testUrl");
    static String url = resourceBundle.getString("drdsUrl");
    static Properties propsHongTest4DateConfigure;
    static {
   /*     propsHongTest4DateConfigure = new Properties();
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_SQL, "false");
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_ROUTE2ALL, "true");
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_TOPN_SLOWSQL, "false");
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_CACHE_PREDEFINE_SQL, "false");
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_UPDATE_MULTI_TABLE, "true");*/
        propsHongTest4DateConfigure.put("dbpool.class", "com.alibaba.druid.pool.DruidDataSource");
        propsHongTest4DateConfigure.put("dbpool.initialSize", "5");
        propsHongTest4DateConfigure.put("dbpool.minIdle", "1");
        propsHongTest4DateConfigure.put("dbpool.maxActive", "50");
        propsHongTest4DateConfigure.put("dbpool.timeBetweenEvictionRunsMillis", "1000");
        propsHongTest4DateConfigure.put("dbpool.minEvictableIdleTimeMillis", "5000");
        propsHongTest4DateConfigure.put("dbpool.testWhileIdle", "true");
        propsHongTest4DateConfigure.put("dbpool.testOnBorrow", "false");
        propsHongTest4DateConfigure.put("dbpool.testOnReturn", "false");
        propsHongTest4DateConfigure.put("dbpool.poolPreparedStatements", "true");
        propsHongTest4DateConfigure.put("dbpool.maxPoolPreparedStatementPerConnectionSize", "20");
        propsHongTest4DateConfigure.put("dbpool.removeAbandoned", "true");
        propsHongTest4DateConfigure.put("dbpool.removeAbandonedTimeout", "18000");
        propsHongTest4DateConfigure.put("dbpool.logAbandoned", "true");
        propsHongTest4DateConfigure.put("dbpool.printSQL", "true");
        propsHongTest4DateConfigure.put("dbpool.validationQueryTimeout", "1");
        propsHongTest4DateConfigure.put("dbpool.validationQuery", "select 2 from dual");
        propsHongTest4DateConfigure.put("dbpool.filters", "slf4j");
        propsHongTest4DateConfigure.put("dbpool.keepAlive", "false");
        propsHongTest4DateConfigure.put("druid.log.stmt", "true");
        propsHongTest4DateConfigure.put("druid.log.stmt.executableSql", "true");
        propsHongTest4DateConfigure.put("druid.log.rs", "true");
        propsHongTest4DateConfigure.put("druid.log.conn", "true");

        //是否开启指标上报
//        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_GATHER_SQL_OPEN, "false");
        //上报间隔时间
//        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_GATHER_SQL_PEROID, "5000");
        // monitor host
//        propsHongTest4DateConfigure.put(CdsConfig4Global.CONFIG_METRICS_REPORT_HOST, "10.222.11.62");
        // monitor port
//        propsHongTest4DateConfigure.put(CdsConfig4Global.CONFIG_METRICS_REPORT_PORT, "8078");

        try {
            Class.forName("com.jdjr.cds.driver.CdsDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static Connection makeConnection(String url,Properties properties) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Test
    public void reportConnPoolMsg() {
        String url = "jdbc:cds://172.24.4.22:8088/dts_source?app=qsw;key=990e9bb942e7c4cc";
        String sql = "select 1 from dts_source_table";
//        String sql = "select 2 from dual";
        Connection connection;
        PreparedStatement preparedStatement;
        try {
//                while (true) {
            connection = makeConnection(url, propsHongTest4DateConfigure);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("system.printt:" + resultSet.getString(1));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            /*Thread.sleep(40000);

            connection = makeConnection(url, propsHongTest4DateConfigure);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()) {
                System.out.println("system.printt:" + resultSet1.getString("id"));
            }
            resultSet1.close();
            preparedStatement.close();
            connection.close();
            Thread.sleep(40000);

            connection = makeConnection(url, propsHongTest4DateConfigure);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                System.out.println("system.printt:" + resultSet2.getString("id"));
            }
            resultSet2.close();
            preparedStatement.close();
            connection.close();*/
            Thread.currentThread().join();
//                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commonTest() {
        String selectSql = "select * from drds_test where id in(1,2,3,4,5) order by id desc";
        Connection connection = DbTools.makeConnection(url, propsHongTest4DateConfigure);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


    }

}
