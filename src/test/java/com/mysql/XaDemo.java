package com.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-27
 */
public class XaDemo {

    public static MysqlXADataSource getDataSource(String url, String user, String password){
        try {
            MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
            mysqlXADataSource.setUrl(url);
            mysqlXADataSource.setUser(user);
            mysqlXADataSource.setPassword(password);
            return mysqlXADataSource;
        } catch (Exception e) {
            return null;
        }
    }
}
