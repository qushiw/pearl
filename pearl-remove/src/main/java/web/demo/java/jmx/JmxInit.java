package web.demo.java.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class JmxInit {

    public void init(){
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("MyMbean:name=ServerInfo");
            mBeanServer.registerMBean(new ServerInfo(), objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
