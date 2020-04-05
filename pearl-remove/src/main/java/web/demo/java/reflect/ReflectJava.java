package web.demo.java.reflect;

import com.web.spring.Abean;

import java.lang.reflect.Constructor;

public class ReflectJava {

    public static void main(String[] args) {
        Class clazz = Abean.class;
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Abean abean = (Abean) constructor.newInstance();
            System.out.println(abean);
        } catch (Exception e) {

        }
    }


}
