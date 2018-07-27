package com.example.kotlin.thread.callableAndFulture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/7/26 15:09
 * Email:cq_816102@163.com
 * Tips:
 * * Source:
 * <a href="http://java-x.blogspot.com.tr/2006/11/java-5-concurrency-callable-and-future.html">
 * http://java-x.blogspot.com.tr/2006/11/java-5-concurrency-callable-and-future.html
 * </a>
 * <p>
 * Till Java 1.4, threads could be implemented by either implementing
 * {@link java.lang.Runnable} or extending {@link java.lang.Thread}.
 * This was quite simple, but had a serious limitation;
 * They have a run method that cannot return values. In order to side-step this,
 * most programmers use side-effects (writing to a file etc.) to mimic returning
 * values to the invoker of the thread. Java 5 introduces the
 * {@link java.util.concurrent.Callable} interface, that allows users to
 * return values from a thread.
 * </p>
 * <p>
 * {@link java.lang.Runnable} vs {@link java.util.concurrent.Callable} :
 * <ul>
 * <li>
 * Runnable Introduced in Java 1.0. Callable<T> Introduced in Java 1.5 as
 * part of
 * {@link java.util.concurrent} library.
 * </li>
 * <li>
 * Runnable cannot be parametrized .Callable is a parametrized type whose type
 * parameter indicates the return type of its run method Classes implementing.
 * </li>
 * <li>
 * Runnable needs to implement run() method, classes implementing Callable needs
 * to implement call() method.
 * </li>
 * <li>
 * Runnable.run() returns no value, Callable.call() returns a value of Type T.
 * </li>
 * <li>
 * Runnable can not throw checked exceptions, Callable can throw checked
 * exceptions.
 * </li>
 * </ul>
 * </p>
 */
public class Worker implements Runnable {

    private Random random = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();


    @Override
    public void run() {
        process();
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
        System.out.println("current list1:"+list1.size()+" list2："+list2.size());
    }

    private void stageTwo() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    private void stageOne() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public static class WorkerThreadPool {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            System.out.println("starting...");
            long start = System.currentTimeMillis();
            Worker worker = new Worker();
            executor.submit(worker);
            executor.submit(worker);
            executor.submit(worker);
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);//等待终止
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("time taken: " + (end - start));
            System.out.println("lis1：" + worker.list1.size() + "； list2: " + worker.list2.size());
        }
    }
}
