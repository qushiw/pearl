package com.jdk;

import java.sql.*;

/**
 * Created by jrqushiwen on 2017-11-16.
 */
public class ExtendsDemo extends Farther{

    public ExtendsDemo(){
//        super("father");
        System.out.println("son");
    }


    public static void main(String[] args) {
        Farther extendsDemo = new ExtendsDemo();

        try {
            Connection connection = DriverManager.getConnection("");
            PreparedStatement preparedStatement = connection.prepareStatement("");
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
