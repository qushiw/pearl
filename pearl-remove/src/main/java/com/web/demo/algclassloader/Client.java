package com.web.demo.algclassloader;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Author: qushiwen
 * @Date: 18-12-28
 * @version: 1.0
 */
public class Client {

    public static void main(String[] args) throws Exception {
        File file = new File("");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        int bufSize = 1024;
        byte[] bytes = new byte[bufSize];
        int len = 0;
        while (-1 != (len = bufferedInputStream.read(bytes, 0, bufSize))) {
            byteArrayOutputStream.write(bytes, 0, len);
        }

        byte[] res = byteArrayOutputStream.toByteArray();
        JarClassLoader jarClassLoader = ClassLoaderWrapper.getClassLoaderWrapper().getDriverJarClassLoader();
        jarClassLoader.add("test", "sql", res);
        Class clazz = jarClassLoader.loadClass("com.jdjr.cds.driver.test.ReloadResSplitAlgMod");

    }


}
