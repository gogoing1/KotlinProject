package com.example.kotlin.thread.threadPool;

/**
 * Created by chenqi on 2018/8/9 17:18
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args){
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }

    public static class Runner extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
