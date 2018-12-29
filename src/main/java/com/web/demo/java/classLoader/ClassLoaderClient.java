package com.web.demo.java.classLoader;

/**
 * @Author: qushiwen
 * @Date: 18-12-29
 * @version: 1.0
 */
public class ClassLoaderClient {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MineClassLoader mineClassLoader = new MineClassLoader("/home/qushiwen/temp/");

        Class clazz = mineClassLoader.loadClass("com.web.demo.java.classLoader.ClassLoaderTest", true);
        ClassLoaderTest classLoaderTest =  (ClassLoaderTest)clazz.newInstance();
        classLoaderTest.a();
    }


}
