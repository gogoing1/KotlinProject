package com.example.kotlin.base;

import android.content.Context;

/**
 * Created by chenqi on 2018/7/14 15:16
 * Email:cq_816102@163.com
 * Tips:
 */
public abstract class BaseDataSource {

    private Context mContext;

    //todo 网络加载库
    //.....

    public BaseDataSource(Context mContext) {
        this.mContext = mContext;
    }
}
