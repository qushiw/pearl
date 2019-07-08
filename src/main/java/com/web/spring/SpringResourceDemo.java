package com.web.spring;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.*;

import java.io.IOException;
import java.util.concurrent.Callable;

public class SpringResourceDemo {
    public static void main(String[] args) throws IOException {
        /*ResourceLoader fileResourceLoader = new FileSystemResourceLoader();
        InputStreamResource resource = (InputStreamResource) fileResourceLoader.getResource("D:\\temp\\test.txt");
        System.out.println(resource.getInputStream());
        System.out.println(resource.contentLength());


        ResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource defaultResource = defaultResourceLoader.getResource("http://www.baidu.com");
        System.out.println(defaultResource.exists());
        System.out.println(defaultResource.getURI().getPath() + ":" + defaultResource.getURI().getPort());*/

        ClassPathResource classPathResource = new ClassPathResource("spring/applicationContext-bean.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        int num = xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
        Abean abean = (Abean) defaultListableBeanFactory.getBean("abean");
        abean.a();
    }


    private static class protocolResolverTest implements  ProtocolResolver {
        @Override
        public Resource resolve(String location, ResourceLoader resourceLoader) {
            return resourceLoader.getResource(location);
        }
    }



    @Test
    public void testB() {
        testA("test", () -> {
            System.out.println("a");
            return "a";
        });
    }




    public void testA(String name, TestI testI) {

        try {
            String result = testI.a();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FunctionalInterface
    private interface TestI {
        String a();
    }
}
