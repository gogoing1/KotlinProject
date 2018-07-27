package com.example.kotlin.thread.interruptingThreads;

/**
 * Created by chenqi on 2018/7/27 16:10
 * Email:cq_816102@163.com
 * Tips:
 */

public class InterruptThread implements Runnable {
    public static void main(String[] args) {
        InterruptThread nm=new InterruptThread();
        Thread thread = new Thread(nm);
        System.out.println("没事干去修电脑吧");
        System.out.println("interrupt state = " + thread.isInterrupted());
        System.out.println("");

        thread.start();
        nm.sleep(1000);
        thread.interrupt();

        System.out.println("");
        System.out.println("别修了地震了...快跑");
        System.out.println("interrupt state = " + thread.isInterrupted());
    }

    public void run() {
        System.out.println("开始修电脑");
        for (int i = 10; i <= 100; i += 10) {
            if(Thread.interrupted()){
                System.out.println("别打断我，我得修完了...");
                System.out.println("");
            }

            System.out.println("修复进度" + i + "%");
            sleep(1000);
        }
        System.out.println("电脑修好了，人呢...");
    }
    /**
     * 自己写个sleep条件循环为了禁止Interrupt对Thread.sleep(x)时的异常抛出
     */
    private void sleep(int step) {
        long time = System.currentTimeMillis();
        while ((System.currentTimeMillis() - time < step)) {
            //模拟耗时操作
        }
    }
}
