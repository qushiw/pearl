package com.web.demo.java.classLoader;

/**
 * @Author: qushiwen
 * @Date: 18-12-29
 * @version: 1.0
 */
public class MineClassLoader extends ClassLoader {
    private String basePath;


    public MineClassLoader(String basePath) {
        this.basePath = basePath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }


}
