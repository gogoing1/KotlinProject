package com.example.kotlin.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.kotlin.model.MvpDataSource;
import com.example.kotlin.net.RequestCallBack;

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
        getLoginStatus();
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void getLoginStatus() {
        mDataSource.getLoginStatus(new RequestCallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if(data){
                    view.showToastMessage("登录成功");
                }else {
                    view.showToastMessage("登录失败");
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                //模拟请求失败的状态...
                view.showToastMessage("请求失败");
            }
        });
    }

}
