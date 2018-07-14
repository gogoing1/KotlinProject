package com.example.kotlin.base;

/**
 * Created by chenqi on 2018/7/13 11:37
 * Email:cq_816102@163.com
 * Tips:MVP view抽象父类
 */
public interface BaseView {

    AbstractActivity getActivityContext();

    void showToastMessage(String msg);
}
