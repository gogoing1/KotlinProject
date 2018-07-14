package com.example.kotlin.base;

/**
 * Created by chenqi on 2018/7/13 11:35
 * Email:cq_816102@163.com
 * Tips:Presenter抽象父类
 */
public abstract class BasePresenter<V extends BaseView> {

    private V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    /**
     * 初始化一些数据
     */
    public abstract void onStart();


    /**
     * 销毁一些数据
     */
    public abstract void onDestory();
}
