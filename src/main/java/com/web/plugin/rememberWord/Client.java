package com.web.plugin.rememberWord;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qsfs on 2017-10-23.
 */
public class Client {

    public static void main(String[] args) {

        WordHolder wordHolder = WordHolder.getInstance();
        Word word = null;
        for (int i=0; i< 10; i++) {
            word = new Word();
            word.setChinese("chinese:" + i);
            word.setEnglish("english:" + i);
            word.setComments("comment:" + i);
            wordHolder.put(word);
        }

        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 1 * 5000;
        timer.scheduleAtFixedRate(new Task(), delay, intevalPeriod);
    }




    private static class Task extends TimerTask{

        @Override
        public void run() {

            LinkedBlockingQueue linkedBlockingQueue = WordHolder.getQueue();
            if (linkedBlockingQueue.isEmpty()) {
                return;
            }

            Iterator<Word> iterator = linkedBlockingQueue.iterator();
            Word word = null;
            while (iterator.hasNext()) {
                word = iterator.next();
                System.out.println("english = " + word.getEnglish() + ", chinese = " + word.getChinese() + ", comment = " + word.getComments());
            }
            System.out.println("------------------------------------------------------");

        }
    }




}
