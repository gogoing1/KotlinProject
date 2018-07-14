package com.example.kotlin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenqi on 2018/7/13 10:44
 * Email:cq_816102@163.com
 * Tips:Fragment抽象父类
 */
public abstract class AbstractFragment extends Fragment {

    protected AbstractActivity mContext;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mContext = (AbstractActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        bindViews(view, savedInstanceState);
        initData();
    }

    public abstract int setContentView();

    protected abstract void initData();

    protected abstract void bindViews(View view, Bundle savedInstanceState);

    protected void toastMessage(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mContext == null) {
            return;
        }
        mContext.toastMessage(msg);
    }
}
