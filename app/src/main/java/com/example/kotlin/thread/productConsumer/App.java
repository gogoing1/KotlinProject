package com.example.kotlin.thread.productConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by chenqi on 2018/8/6 16:27
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);//阻塞队列

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(30000);
        System.exit(0);
    }

    private static void product() throws InterruptedException {
        Random random = new Random();
        while (true){
            blockingQueue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true){
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                Integer value = blockingQueue.take();
                System.out.println("Taken value: " + value + "; Queue size is: " + blockingQueue.size());
            }
        }
    }

}
