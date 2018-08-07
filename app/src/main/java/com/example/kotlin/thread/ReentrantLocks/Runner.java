package com.example.kotlin.thread.ReentrantLocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenqi on 2018/8/6 17:07
 * Email:cq_816102@163.com
 * Tips:
 /**
 * Source:<em>
 * http://www.journaldev.com/2377/java-lock-example-and-concurrency-lock-vs-synchronized</em>
 * <p>
 * {@link java.util.concurrent.locks.Lock}:
 * { @link java.util.concurrent.locks.Lock }:
 * 这是Lock API的基本接口。它提供了所有的特性
 同步关键字和其他创建不同条件的方法
 为了锁定，为线程提供超时等待锁。的一些
 重要的方法是@link java.util.concurrent.lock.lock lock（）
 获取锁，@link java.util.concurrent.lock.lock解锁（）释放
 锁，@link java.util.concurrent.locklock trylock（）等待锁
 在一段时间内，
 * { @link java.util.concurrent.locks.Lock # newCondition()}
 创造条件等。
 * < p >
 * { @link java.util.concurrent.locks.ReentrantLock }:
 这是最广泛使用的锁实现类
 *接口。该类以类似的方式实现Lock接口
 * synchronized关键字。(见App.java更多)
 * < p >
 * { @link java.util.concurrent.locks.Condition }:
 条件对象类似于Object wait-notify模型
 额外的特性来创建不同的等待集。对象是一个条件
 总是由锁对象创建。一些重要的方法是
 * { @link java.util.concurrent.locks.Condition #等待()}
 这类似于@link Object wait（）。
 * { @link java.util.concurrent.locks.Condition #信号()}
 * { @link java.util.concurrent.locks.Condition # signalAll()}
 这类似于
 @link Object notify（）和@link Object notifyall（）方法。
  * < br > < br >
 带有小注释的代码来自
  * < a href = " http://www.caveofprogramming.com/youtube/ " >
  * < em > http://www.caveofprogramming.com/youtube/ < / em >
  * < / >
  * < br >
 也可以免费提供
  * < a href = " https://www.udemy.com/java-multithreading/?couponCode=FREE " >
  * < em > https://www.udemy.com/java-multithreading/?couponCode=FREE < / em >
  * < / >
 *
 @作家z.b.Celik.celik.berkay@gmail.com
 */
public class Runner {
    int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment(){
        for(int i=0;i<10000;i++){
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("waiting...");

        cond.await();
        System.out.println("woken up !!!");

        try {
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");
        cond.signal();
        try {
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is :"+count);
    }


}
