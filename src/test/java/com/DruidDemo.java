
import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by jrqushiwen on 2017/9/10.
 */
public class DruidDemo {
    public static void main(String[] args) {
        try {
            DruidDataSource druidDataSource = new DruidDataSource();

            Connection connection = druidDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from test");
        } catch (Exception e) {

        }

    }

}
