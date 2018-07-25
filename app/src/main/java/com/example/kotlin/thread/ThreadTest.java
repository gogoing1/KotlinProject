package com.example.kotlin.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by chenqi on 2018/7/23 10:01
 * Email:cq_816102@163.com
 * Tips:
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception{
        ThreadA a = new ThreadA();
        ThreadA b = new ThreadA();
        a.run();
        b.run();

        ThreadB t = new ThreadB();
        t.run();

        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            list.add(es.submit(new ThreadC((int)(Math.random()*100))));
        }
        int sum =0;
        for(Future<Integer> f : list){
            while (!f.isDone());
            sum += f.get();
        }
        System.out.println("sum: " + sum);
    }


    public static int x = 0;

    public static void addX() {
        x++;
        System.out.println(Thread.currentThread() + ": " + x);
    }


    public static class ThreadA implements Runnable {

        @Override
        public synchronized void run() {
            addX();
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public synchronized void run() {
            super.run();
            try {
                addX();
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadC implements Callable {
        private int upperBounds;

        public ThreadC(int upperBounds) {
            this.upperBounds = upperBounds;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < upperBounds; i++) {
                sum += 1;
            }
            return sum;
        }
    }
}
