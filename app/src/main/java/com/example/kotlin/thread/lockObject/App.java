package com.example.kotlin.thread.lockObject;

/**
 * Created by chenqi on 2018/7/27 16:53
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Synchronized Objects: ");
        Worker worker = new Worker();
        worker.main();

        System.out.println("");

        System.out.println("Synchronized Methods: ");
        WorkerMethodsSynchronized worker2 = new WorkerMethodsSynchronized();
        worker2.main();
    }
}
