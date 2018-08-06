package com.example.kotlin.thread.LowLevelProductConsumer;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by chenqi on 2018/8/6 15:45
 * Email:cq_816102@163.com
 * Tips:
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void product() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value);
                System.out.println("value = " + value);
                value++;
                lock.notify();
            }
        }
    }


    public void cousumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                int value = list.removeFirst();
                System.out.println("remove value by consumer = "+value +" now list size is :"+list.size());
                lock.notify();
            }
            Thread.sleep(1000);
        }
    }

}
