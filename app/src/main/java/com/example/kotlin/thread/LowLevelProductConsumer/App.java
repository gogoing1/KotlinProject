package com.example.kotlin.thread.LowLevelProductConsumer;

/**
 * Created by chenqi on 2018/7/30 23:11
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.cousumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread1.start();

        Thread.sleep(30000);
        System.exit(0);
    }
}
