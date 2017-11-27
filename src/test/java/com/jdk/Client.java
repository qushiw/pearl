package com.jdk;

import com.arithmetic.ArrangeCombination;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

//        ExecuteStatement executeStatement = new ExecuteStatement();
//        Boolean flag =  executeStatement.execute(new ConnCallBack<Boolean>() {
//            @Override
//            public Boolean callBack() {
//                return true;
//            }
//        });
//        System.out.println(flag);


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packageName = "com/arithmetic";
        Enumeration<URL> enumeration = classLoader.getResources(packageName);
        while (enumeration.hasMoreElements()) {
            URL url = enumeration.nextElement();
            File file = new File(url.getFile());
            File[] files = file.listFiles();
            for (File file1 : files) {
                String className = packageName + "." + file1.getName().substring(0, file1.getName().length() - 6);

                className = className.replaceAll("/", ".");
                Class c = classLoader.loadClass(className);
                ArrangeCombination arrangeCombination = (ArrangeCombination) c.newInstance();
                String[] a = {"1", "2", "3"};

//                arrangeCombination.sort(a, new ArrayList<String[]>());

            }
        }
    }



}
