package com.example.kotlin.net;

/**
 * Created by chenqi on 2018/7/14 15:28
 * Email:cq_816102@163.com
 * Tips:
 */
public interface NetCallBackListener<T> {

    void onSuccess(int code,T result);

    void onFailure(int errCode,String msg);
}
