package com.ssm.Server.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author jrqushiwen
 * @date 2020/5/5 16:15
 */
public class AopClient {

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new FileSystemXmlApplicationContext("D:\\workspace\\mine\\pearl\\pearl-ssm\\src\\main\\java\\com\\ssm\\Server\\spring\\aop\\applicationContext.xml");

        TargetObjectInterface targetObject = (TargetObjectInterface) applicationContext.getBean("targetObject");

        targetObject.method();
    }

}
