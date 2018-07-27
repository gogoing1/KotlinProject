package com.example.kotlin.thread.deadLock;

/**
 * Created by chenqi on 2018/7/26 18:05
 * Email:cq_816102@163.com
 * Tips: 模拟账户存取款实体类
 */
public class Account {

    private int balance = 10000;

    /**
     * 存款
     *
     * @param amount
     */
    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * 取款
     *
     * @param amount
     */
    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * acc1 转账给 acc2
     *
     * @param acc1
     * @param acc2
     * @param amount
     */
    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
