package web.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by jrqushiwen on 2017/9/10.
 */

public class ApplicationHold implements ApplicationContextAware {


    private static ApplicationContext applicationContext;
    private static Object lock = new Object();

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
