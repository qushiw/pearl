package com.web.demo.lambda;


/**
*
* @author qushiwen
* @create 2018-01-17
* @version 1.0
**/
public class CallBackDemo {

    public interface CallBackInterface{
        public ProxyExecuteDemo doAction(ProxyExecuteDemo proxyExecuteDemo);
    }



    private static class ExecuteDemo {
        public void execute(CallBackInterface callBackInterface) {

        }
    }

    private static class ProxyExecuteDemo {

    };

    public static void main(String[] args) {
        ExecuteDemo executeDemo = new ExecuteDemo();

        executeDemo.execute((ProxyExecuteDemo proxyExecuteDemo) -> {
            System.out.println("ceshi");
            return proxyExecuteDemo;
        });



    }


}
