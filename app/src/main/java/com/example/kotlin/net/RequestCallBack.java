package com.example.kotlin.net;

/**
 * Created by chenqi on 2018/7/14 15:28
 * Email:cq_816102@163.com
 * Tips:
 */
public interface RequestCallBack<T> {

    void onSuccess(T data);

    void onFailure(int code,String msg);
}
