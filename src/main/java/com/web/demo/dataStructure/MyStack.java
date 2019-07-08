package com.web.demo.dataStructure;


public class MyStack {

    private int initSize = 16;
    private int[] data;

    private int dataSize = 0;

    private int index = 0;

    /** Initialize your data structure here. */
    public MyStack() {
        data = new int[initSize];
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if ((index +1) == initSize) {
            initSize = initSize * 2;
            int[] newData = new int[initSize];
            for (int i=0; i<data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        data[index++] = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int temp = data[--index];
        return temp;
    }

    /** Get the top element. */
    public int top() {
        int i = index;
        i = i-1;
        return data[i];
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return index == 0;
    }


}
