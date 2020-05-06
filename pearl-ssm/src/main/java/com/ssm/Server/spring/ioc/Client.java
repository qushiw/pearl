package com.ssm.Server.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author jrqushiwen
 * @date 2020/5/4 22:33
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new FileSystemXmlApplicationContext("D:\\workspace\\mine\\pearl\\pearl-ssm\\src\\main\\java\\com\\ssm\\Server\\spring\\ioc\\applicationContext.xml");

        AObject aObject = (AObject) applicationContext.getBean("a");
        aObject.a();

    }
}
