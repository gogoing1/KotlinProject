package com.example.kotlin.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenqi on 2018/7/26 16:33
 * Email:cq_816102@163.com
 * Tips:
 * * {@link java.util.concurrent.CountDownLatch} 同步线程活动的java类
 * <br><br>
 * Source:
 * <em>http://stackoverflow.com/questions/17827022/what-is-countdown-latch-in-java-multithreading</em><br>
 *
 * Any thread, usually main thread of application, which calls
 * {@link java.util.concurrent.CountDownLatch#await()} will wait until count reaches zero or its interrupted
 * by another thread. All other thread are required to do count down by calling
 * {@link java.util.concurrent.CountDownLatch#countDown()} once they are completed or ready.
 * <br>
 * As soon as count reaches zero, Thread awaiting starts running. One of the
 * disadvantage of {@link java.util.concurrent.CountDownLatch} is that it's
 * not reusable once the count reaches to
 * zero you can not use {@link java.util.concurrent.CountDownLatch} any more.
 * <br><br>
 * Use {@link java.util.concurrent.CountDownLatch} when one thread, like main
 * thread, require to wait for one or more threads to complete, before it can
 * start processing.
 * <br><br>
 * Classical example of using {@link java.util.concurrent.CountDownLatch} in
 * Java is any server side core  Java application which uses services
 * architecture, where multiple services
 * are provided by multiple threads and application can not start processing
 * until all services have started successfully.
 * <br><br>
 * Codes with minor comments are from <em>http://www.caveofprogramming.com/youtube/</em><br>
 * also freely available at
 * <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 */
public class App{

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            executor.submit(new Processor(latch));
        }
        executor.shutdown();

        try {
            latch.await();//调用await() 方法的线程会被挂起，它会等待直到count值为0的时候才执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("completed");
    }
}

class Processor implements Runnable{

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("starting...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
