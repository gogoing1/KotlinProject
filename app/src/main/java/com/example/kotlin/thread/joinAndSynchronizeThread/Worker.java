package com.example.kotlin.thread.joinAndSynchronizeThread;

/**
 * Created by chenqi on 2018/7/27 16:35
 * Email:cq_816102@163.com
 * Tips:
 */
public class Worker {

    private int count = 0;

    public static void main(String[] args){
            Worker w = new Worker();
            w.doWork();
    }

    /**
     * synchronized 关键字去掉与不去掉的区别
     * @param threadName
     */
    public synchronized void increment(String threadName){
        count ++;
        if(threadName.contains("1")){
            threadName += "---------------------";
        }
        System.out.println("Thread in process "+ threadName +" and count is "+ count);
    }

    private void doWork() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<30;i++){
                    increment(Thread.currentThread().getName());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<30;i++){
                    increment(Thread.currentThread().getName());
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count is "+ count);
    }
}
