package com.example.kotlin.thread.Semaphores;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/8/8 16:43
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        for(int i=0;i<20;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    //Connectionn.getInstance().connect();
                    Connection.getInstance().connect();
                }
            });
        }

        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
}
