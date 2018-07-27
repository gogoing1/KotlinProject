package com.example.kotlin.thread.callableAndFulture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/7/26 16:20
 * Email:cq_816102@163.com
 * Tips:
 */
public class CallableImpl implements Callable<Integer>{

    private int name;

    public CallableImpl(int name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread : " + getMyName() + " value is : " + i);
        }
        return getMyName();
    }

    public int getMyName() {
        return name;
    }
}

class CalableTest{
    public static void main(String[] args){
        CallableImpl call = new CallableImpl(2);
        ExecutorService executor = new ScheduledThreadPoolExecutor(1);
        Future<Integer> future = executor.submit(call);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Future value: "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
