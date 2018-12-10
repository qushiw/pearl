package com.cdsDriver;

import com.jdjr.cds.driver.CdsConfig4Cluster;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */
public class DriverJdbcTest {

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("testUrl");
    static String url = resourceBundle.getString("drdsUrl");
    static Properties propsHongTest4DateConfigure;
    static {
        propsHongTest4DateConfigure = new Properties();
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_SQL, "false");
        propsHongTest4DateConfigure.put(CdsConfig4Cluster.CONFIG_ROUTE2ALL, "true");
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
        System.out.println(4 >>> 1);
    }

}
