package com.thread;

import java.util.LinkedList;

/**
*
* @author qushiwen
* @create 2017-12-05
* @version 1.0
**/
public class TaskThreadGroup extends ThreadGroup {
    private boolean isClose = false;
    private LinkedList<Runnable> workGroup;
    private static int threadPoolId;
    private int threadId;

    public TaskThreadGroup(String name, Integer poolSize) {
        super(name);
        workGroup = new LinkedList<Runnable>();
        for (int i=0;i<poolSize;i++) {
            new Worker().start();
        }
    }



    private synchronized void executeTask (Runnable task) {
        if (isClose) {
            throw new IllegalStateException("the work thread pool is closed.");
        }
        if (task != null) {
            workGroup.add(task);
            notify();
        }
    }


    private synchronized Runnable getTask() throws InterruptedException {
        while (workGroup.size() == 0) {
            if (isClose) {
                return null;
            }
            wait();
        }

        return workGroup.removeFirst();
    }


    public void close(){
        if (isClose) {
            isClose = true;
            workGroup.clear();
            interrupt();
        }
    }


    public void join(){
        synchronized (this) {
            isClose = true;
            notifyAll();
        }

        System.out.println("activeCount()" + activeCount());
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);

        System.out.println("-----" + threads.length);
        System.out.println("-----" + count);

        for (int i=0; i<count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private class Worker extends Thread {

        public Worker(){
            super("thread pool id = " + threadId);
        }

        @Override
        public void run() {
            while (!isInterrupted()){
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task == null) {
                    return;
                }
                task.run();
            }
        }
    }


    private static Runnable createTask(final int taskId){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("task:" + taskId + ":start");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task:" + taskId + ":end");
            }
        };
    }

    public static void main(String[] args) {
        int numTask = 3;
        int poolSize = 5;
        TaskThreadGroup taskThreadGroup = new TaskThreadGroup("pool Name", poolSize);

        for (int i=0; i<numTask; i++) {
            taskThreadGroup.executeTask(createTask(i));
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskThreadGroup.join();
//


//        ThreadGroup threadGroup = new ThreadGroup("threadGroup-1");
//        Thread t1 = new Thread(threadGroup, new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(50000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Thread t2 = new Thread(threadGroup, new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(50000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//        t1.start();
//        t2.start();
//
//        System.out.println(threadGroup.activeCount());
    }

}
