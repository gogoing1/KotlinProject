package com.example.kotlin.thread.callableAndFulture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * Created by chenqi on 2018/7/26 15:58
 * Email:cq_816102@163.com
 * Tips:
 *  * {@link java.util.concurrent.Callable} and
 * {@link java.util.concurrent.Future}
 * in Java to get results from your threads and to allow
 * your threads to throw exceptions. Plus, Future allows you to control your
 * threads, checking to see if they’re running or not, waiting for results and
 * even interrupting them or de-scheduling them.
 * <p>
 * {@link java.lang.Runnable}
 * is the default abstraction for creating a task in Java. It has a single
 * method {@link Runnable#run()}
 * that accepts no arguments and returns no value, nor it can throw
 * any checked exception. To overcome these limitations, Java 5 introduced a new
 * task abstraction through {@link java.util.concurrent.Callable} interface.
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 * <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 */
public class App {
    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws TimeoutException {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if(duration>2000){
                    throw new TimeoutException("sleeping for too long...");
                }

                System.out.println("starting...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("finished...");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            TimeoutException ex = (TimeoutException) e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
