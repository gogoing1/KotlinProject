package com.example.kotlin.thread.Semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by chenqi on 2018/8/8 16:43
 * Email:cq_816102@163.com
 * Tips:
 */
public class Connection {
    private static Connection instance = new Connection();
    private Semaphore sem = new Semaphore(10, true);
    private int connections = 0;

    public Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
            doConnect();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections (max 10 allowed): " + connections);

            try {
                System.out.println("Working on connections " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                connections--;
                System.out.println("I'm done " + Thread.currentThread().getName() + " Connection is released , connection count: " + connections);
                System.out.println("");
            }
        }
    }

}
