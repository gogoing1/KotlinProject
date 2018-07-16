package com.example.kotlin.model;

import android.content.Context;

import com.example.kotlin.model.bean.Student;
import com.example.kotlin.net.NetCallBackListener;
import com.google.gson.reflect.TypeToken;

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
    public void getLoginStatus(NetCallBackListener<Boolean> cb) {
        String key[] = new String[]{};
        String value[] = new String[]{};
        String url = "http://www.baidu.com";
        api.postToApi(key,value,url,cb);
    }
}
