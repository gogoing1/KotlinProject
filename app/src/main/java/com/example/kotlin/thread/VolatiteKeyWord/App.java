package com.example.kotlin.thread.VolatiteKeyWord;

import java.util.Scanner;

/**
 * Created by chenqi on 2018/8/9 17:33
 * Email:cq_816102@163.com
 * Tips:
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Processor p = new Processor();
        p.start();
        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        p.shutdown();
    }
}

class Processor extends Thread{

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running){
            System.out.println("Running");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}
