package com.example.kotlin.thread.deadLock;

/**
 * Created by chenqi on 2018/7/27 11:24
 * Email:cq_816102@163.com
 * Tips:
 */
public class SimpleDataLock {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    private int index;

    public static void main(String[] args) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 2:  hoading lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Thread 2:  hoading lock 2 & 1...");
                }
            }
        }
    }
}
