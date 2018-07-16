package com.example.kotlin.model;

import android.content.Context;

import com.example.kotlin.base.BaseDataSource;
import com.example.kotlin.net.NetCallBackListener;

/**
 * Created by chenqi on 2018/7/14 15:19
 * Email:cq_816102@163.com
 * Tips:数据接口抽象类
 */
public abstract class MvpDataSource extends BaseDataSource{

    public MvpDataSource(Context mContext) {
        super(mContext);
    }

    /**
     * 模拟获取登陆状态
     */
    public abstract void getLoginStatus(NetCallBackListener<Boolean> cb);

}
