package com.example.kotlin.rxjava;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by chenqi on 2018/7/20 16:45
 * Email:cq_816102@163.com
 * Tips:Rxjava
 */
public class RxJavaTest {

    private static final String TAG = RxJavaTest.class.getSimpleName();

    public static void main(String args[]) {

        Observable.just("Hello world")
                .subscribe(word -> {
                    System.out.println("got " + word + " @ " + Thread.currentThread().getName());
                });

        //java8 lambdas 写法
        Flowable.just("hello world").subscribe(System.out::println);

        //普通写法
        Flowable.just("hello world").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print(s);
            }
        });
    }
}
