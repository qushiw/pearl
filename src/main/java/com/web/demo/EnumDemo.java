package com.web.demo;


/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public enum  EnumDemo implements EnumInterfaceDemo{

    A(1) {
        public void a(){
            System.out.println("i am a");
        }
    },
    B(2) {
        public void a(){
            System.out.println("i am b");
        }
    };

    private int num;

    EnumDemo(int num) {
        this.num = num;
    }

    public void getNum() {
        System.out.println(num);
    }

    public static void main(String[] args) {
        EnumInterfaceDemo enumDemo = EnumDemo.A;
        enumDemo.a();
        int i = 1;
        try {
            assert i == 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
//        EnumSet enumSet = EnumSet.allOf(EnumDemo.class);
//        System.out.println(enumSet.contains(EnumDemo.A));

     }
}
