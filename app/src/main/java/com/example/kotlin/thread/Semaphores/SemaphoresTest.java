package com.example.kotlin.thread.Semaphores;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by chenqi on 2018/8/9 11:50
 * Email:cq_816102@163.com
 * Tips:
 */
public class SemaphoresTest {
    Random random = new Random();
    Semaphore sp = new Semaphore(2,false);//创建非公平 信号量锁

    public class SemahporesRun implements Runnable{
        String id;

        public SemahporesRun(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                sp.acquire();
                System.out.println("Thread " + id + " is working");
                Thread.sleep(random.nextInt(1000));
                sp.release();
                System.out.println("Thread " + id + " is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
