package com.web.plugin.rememberWord;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qsfs on 2017-10-23.
 */
public class WordHolder {

    private static LinkedBlockingQueue<Word> wordLinkedBlockingQueue = new LinkedBlockingQueue<Word>();

    private static WordHolder wordHolder = new WordHolder();

    public static WordHolder getInstance(){
        return wordHolder;
    }


    public void put(Word word){
        try {
            wordLinkedBlockingQueue.put(word);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static LinkedBlockingQueue<Word> getQueue(){
        return wordLinkedBlockingQueue;
    }


}
