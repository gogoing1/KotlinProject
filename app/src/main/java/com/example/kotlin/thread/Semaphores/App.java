package com.example.kotlin.thread.Semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2018/8/8 16:43
 * Email:cq_816102@163.com
 * Tips:
 *
 * * { @link java.util.concurrent.Semaphore }
 主要用于限制同步线程的数量
 可以访问资源，但也可以使用它们来实现死锁
 恢复系统，因为有一个许可证的信号量基本上是一个锁
 可以从其他线程中解锁。
 * < br >
 *来源:
 * < a href = " http://stackoverflow.com/questions/771347/what-is-mutex-and-semaphore-in-java-what-is-the-main-difference " >
 * http://stackoverflow.com/questions/771347/what-is-mutex-and-semaphore-in-java-what-is-the-main-difference
 * < / >
 * < p >
 互斥量（或初始化为1的信号量;也就是说只有一个资源）
 基本上是相互排斥;只有一个线程可以获得资源
 同时，所有试图获取资源的线程都被阻塞了
 直到拥有资源的线程被释放。
 * < p >
 信号量用于控制执行线程的数量。将会有
 固定的资源集。资源计数每次都会递减
 当一个线程拥有相同的东西时。当信号量计数达到0时，没有其他
 线程被允许获取资源。线程被阻塞，直到
 其他拥有资源发布的线程。
 * < / p >
 * < p >
 简而言之，主要的区别在于有多少线程被允许获得
 *资源。
 多做一些深入的解释
 *互斥对象——它的一个。信号量——它的定义计数（和信号量一样多
 *数量)
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService es = Executors.newCachedThreadPool();
//
//        for(int i=0;i<20;i++){
//            es.submit(new Runnable() {
//                @Override
//                public void run() {
//                    //Connectionn.getInstance().connect();
//                    Connection.getInstance().connect();
//                }
//            });
//        }
//
//        es.shutdown();
//        es.awaitTermination(1, TimeUnit.DAYS);

        //Demo2
        SemaphoresTest se = new SemaphoresTest();
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(se.new SemahporesRun("1"));
        es.submit(se.new SemahporesRun("2"));
        es.submit(se.new SemahporesRun("3"));
        es.submit(se.new SemahporesRun("4"));
        es.submit(se.new SemahporesRun("5"));
        es.submit(se.new SemahporesRun("6"));
        es.submit(se.new SemahporesRun("7"));
        es.submit(se.new SemahporesRun("8"));
        es.submit(se.new SemahporesRun("9"));
        es.shutdown();
    }
}
