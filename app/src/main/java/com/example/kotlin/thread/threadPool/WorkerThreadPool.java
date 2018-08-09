package com.example.kotlin.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/8/9 17:19
 * Email:cq_816102@163.com
 * Tips:
 */
public class WorkerThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();

        Worker worker = new Worker();
        for (int i = 0; i < 2; i++) {
            executorService.submit(worker);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        Long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + worker.list1.size() + "; List2: " + worker.list2.size());
    }
}
