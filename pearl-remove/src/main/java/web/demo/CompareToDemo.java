package web.demo;


import java.util.Arrays;
import java.util.Random;

/**
*
* @author qushiwen
* @create 2018-01-21
* @version 1.0
**/
public class CompareToDemo {


    public static void main(String[] args) {
        A[] arr = new A[5];
        Random random = new Random();
        for (int i=0; i<arr.length; i++) {
            A a = new A();
            a.setNum(random.nextInt(100));
            arr[i] = a;
        }

        Arrays.sort(arr);

        for (A a : arr) {
            System.out.println(a);
        }




    }




    private static class A implements Comparable {

        private Integer num;

        public void setNum(Integer num) {
            this.num = num;
        }


        @Override
        public int compareTo(Object o) {
            A a = (A)o;
            if (num > a.num) {
                return 1;
            } else if (num == a.num) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "num:" + num;
        }
    }
}
