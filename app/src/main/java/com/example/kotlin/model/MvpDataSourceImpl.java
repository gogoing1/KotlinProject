package com.example.kotlin.model;

import android.content.Context;

import com.example.kotlin.net.RequestCallBack;

/**
 * Created by chenqi on 2018/7/14 15:19
 * Email:cq_816102@163.com
 * Tips:MvpDataSource 实体类
 */
public class MvpDataSourceImpl extends MvpDataSource {

    public MvpDataSourceImpl(Context mContext) {
        super(mContext);
    }

    @Override
    public void getLoginStatus(RequestCallBack<Boolean> cb) {
        //Todo 模拟网络请求...
        cb.onSuccess(true);
    }
}
