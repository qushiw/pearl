package com;

import java.util.Arrays;

/**
 * Created by jrqushiwen on 2017-10-21.
 */
public class SystemCopyDemo {

    public static void main(String[] args) {

        Integer[] o1 = {1,2,3,4};

        Integer[] o2 = {5,6,7,8};

        o1 = Arrays.copyOf(o1, 8);

        for (Integer o : o1) {
            System.out.println(o);
        }

        System.arraycopy(o2, 0, o1, o1.length/2, o2.length);

        for (Integer o : o1) {
            System.out.println(o);
        }
    }


}
