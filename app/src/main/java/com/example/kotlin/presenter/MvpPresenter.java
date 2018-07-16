package com.example.kotlin.presenter;

import com.example.kotlin.model.MvpDataSource;
import com.example.kotlin.net.NetCallBackListener;

/**
 * Created by chenqi on 2018/7/14 14:31
 * Email:cq_816102@163.com
 * Tips:
 */
public class MvpPresenter extends MvpContract.Presenter{
    private static final String TAG = MvpDataSource.class.getSimpleName();

    private MvpDataSource mDataSource;

    public MvpPresenter(MvpContract.View view, MvpDataSource mDataSource) {
        super(view);
        this.mDataSource = mDataSource;
    }

    @Override
    public void onStart() {
        //getLoginStatus();
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void getLoginStatus() {
        mDataSource.getLoginStatus(new NetCallBackListener<Boolean>() {
            @Override
            public void onSuccess(int code, Boolean result) {

            }

            @Override
            public void onFailure(int errCode, String msg) {

            }
        });
    }

}
