package com.example.kotlin.thread.deadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenqi on 2018/7/26 18:11
 * Email:cq_816102@163.com
 * Tips:
 */
public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();//ReentrantLock 重入锁
    private Lock lock2 = new ReentrantLock();

    /**
     * acquire （获得；取得；学到；捕获）锁
     *
     * @param firstLock
     * @param secondLock
     */
    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {

            boolean gotFirstLocks = false;
            boolean gotSecondLocks = false;

            try {
                /**
                 * tryLock() which will only acquire a lock if it’s available
                 * and not already acquired by another thread and tryLock(long
                 * time,TimeUnit unit), which will try to acquire a lock and, if
                 * it's unavailable wait for the specified timer to expire
                 * before giving up
                 */
                gotFirstLocks = firstLock.tryLock();
                gotSecondLocks = secondLock.tryLock();
            } finally {
                if (gotFirstLocks && gotSecondLocks) {
                    return;
                } else if (gotFirstLocks) {
                    firstLock.unlock();
                } else if (gotSecondLocks) {
                    secondLock.unlock();
                }
            }

            // Locks not acquired
            Thread.sleep(1);
        }
    }


    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            //上锁
            acquireLocks(lock1, lock2);

            try {
                //acc1 给 acc2 转账
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock2, lock1);

            try {
                //acc1 给 acc2 转账
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("account 1 balance : " + acc1.getBalance());
        System.out.println("account 2 balance : " + acc2.getBalance());
        System.out.println("total balance : " + (acc1.getBalance() + acc2.getBalance()));
    }
}
