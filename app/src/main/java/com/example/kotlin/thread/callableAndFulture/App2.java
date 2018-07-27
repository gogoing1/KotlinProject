package com.example.kotlin.thread.callableAndFulture;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/7/26 16:08
 * Email:cq_816102@163.com
 * Tips:
 * Understanding Callable
 */
public class App2 {

    public static void main(String[] args)throws InterruptedException{
        ArrayList list = new ArrayList();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future;

        for(int i=0;i<10;i++){
            future = executor.submit(new MyCallable(i));
            try {
                list.add(future.get());
            } catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        for(int i=0;i<list.size();i++){
            System.out.println("List Values " + i + " Value: " + list.get(i));
        }
    }

}

class MyCallable implements Callable<Integer> {

    int value;

    public MyCallable(int value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        Integer sum = 0;
        for(int i=0;i<value;i++){
            sum += i;
        }
        System.out.println("sum in callable.call() "+sum);
        return sum;
    }
}