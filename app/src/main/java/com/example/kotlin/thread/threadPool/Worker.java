package com.example.kotlin.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenqi on 2018/8/9 17:20
 * Email:cq_816102@163.com
 * Tips:
 */
public class Worker implements Runnable {

    private Random random = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();

    @Override
    public void run() {
        process();
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    private void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //do your work here
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    private void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //do your work here
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }
}
