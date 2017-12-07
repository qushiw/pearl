package com.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

/**
*
* @author qushiwen
* @create 2017-11-27
* @version 1.0
**/
public class JtaDemo {

    public static void main(String[] args) {
        String url1 = "jdbc:mysql://127.0.0.1:3306/global";
        String url2 = "jdbc:mysql://127.0.0.1:3306/global";


        try {
            MysqlXADataSource mysqlXADataSource1 = XaDemo.getDataSource(url1, "root", "123456");
            MysqlXADataSource mysqlXADataSource2 = XaDemo.getDataSource(url2, "root", "123456");

            XAConnection xaConnection = mysqlXADataSource1.getXAConnection();
            XAResource xaResource = xaConnection.getXAResource();
            Connection connection = xaConnection.getConnection();
            Statement statement = connection.createStatement();


            XAConnection xaConnection1 = mysqlXADataSource2.getXAConnection();
            XAResource xaResource1 = xaConnection1.getXAResource();
            Connection connection1 = xaConnection1.getConnection();
            Statement statement1 = connection1.createStatement();

            Xid xid = new MyXid(100, new byte[0x01], new byte[0x02]);
            Xid xid1 = new MyXid(100, new byte[0x11], new byte[0x12]);

            xaResource.start(xid, XAResource.TMNOFLAGS);
            statement.execute("insert into user(id,name) value('2','qsfs')");
            xaResource.end(xid, XAResource.TMSUCCESS);

            xaResource1.start(xid1, XAResource.TMNOFLAGS);
            statement1.execute("insert into user(id,name) value('3','qsfs')");
            xaResource1.end(xid1, XAResource.TMSUCCESS);

            int ret = xaResource.prepare(xid);
            int ret1 = xaResource1.prepare(xid1);

            if (ret == XAResource.XA_OK && ret1 == XAResource.XA_OK) {
                xaResource.commit(xid, false);
                xaResource.commit(xid1, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }





}
