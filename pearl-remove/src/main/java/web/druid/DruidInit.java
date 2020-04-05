package web.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.web.common.ApplicationHold;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by jrqushiwen on 2017/9/10.
 */

public class DruidInit implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DruidDataSource druidDataSource = (DruidDataSource)ApplicationHold.getApplicationContext().getBean("druidDataSource");

            Statement statement = druidDataSource.getConnection().createStatement();
            ResultSet rset = statement.executeQuery("select * from test");//该数据库中有 users 表
            System.out.println(rset.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
