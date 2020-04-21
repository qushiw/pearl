package com.ssm.Server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: qushiwen
 * @Date: 20-4-20
 * @version: 1.0
 */
public class MainClient {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) applicationContext.getBean("baiTiaoTransactionManager");


        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) applicationContext.getBean("basicDtSource");
        try {
            Connection connection = driverManagerDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from bt_quickpay_user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
