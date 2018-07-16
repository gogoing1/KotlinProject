package com.example.kotlin.base;

import android.content.Context;

import com.example.kotlin.net.NetActionApi;

/**
 * Created by chenqi on 2018/7/14 15:16
 * Email:cq_816102@163.com
 * Tips:
 */
public abstract class BaseDataSource {

    private Context mContext;
    public NetActionApi api;

    public BaseDataSource(Context mContext) {
        this.mContext = mContext;
        api = new NetActionApi(mContext);
    }
}
