package web.demo.mock;

import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
*
* @author qushiwen
* @create 2018-01-06
* @version 1.0
**/
public class Demotest extends EasyMockSupport {


    @Rule
    public EasyMockRule easyMockRule = new EasyMockRule(this);

    @Mock
    private Collaborator collaborator;

    @TestSubject
    private ClassTested classTested = new ClassTested();

    @Test
    public void addDocument() {
        IMocksControl iMocksControl = EasyMock.createControl();
        try {

            ResultSet resultSet = EasyMock.createMock(ResultSet.class);
//            ResultSet resultSet = iMocksControl.createMock(ResultSet.class);
            resultSet.getString("1");
            EasyMock.expectLastCall().andStubReturn("my test");
            EasyMock.replay(resultSet);
            System.out.println(resultSet.getString("1"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void justTest() throws IOException {
        System.setSecurityManager(new SecurityManager());

        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            @Override
            public Object run() {
                File file = new File("D:\\log\\a.txt");
                try {
                    file.createNewFile();
                } catch (IOException e) {

                    e.printStackTrace();
                }
                return null;
            }
        });
    }


}
