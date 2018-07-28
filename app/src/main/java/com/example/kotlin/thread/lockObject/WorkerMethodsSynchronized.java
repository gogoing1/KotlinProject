package com.example.kotlin.thread.lockObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenqi on 2018/7/27 16:54
 * Email:cq_816102@163.com
 * Tips:
 */
public class WorkerMethodsSynchronized {
    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> process());
        Thread t2 = new Thread(() -> process());

        t1.start();
        t2.start();

        //等待t1，t2线程执行完毕
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("list1 " + list1.size() + "    list2 " + list2.size());
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public synchronized void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public synchronized void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

}
