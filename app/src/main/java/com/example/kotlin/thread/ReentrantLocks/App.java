package com.example.kotlin.thread.ReentrantLocks;

/**
 * Created by chenqi on 2018/8/6 17:07
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        runner.finished();
    }
}
