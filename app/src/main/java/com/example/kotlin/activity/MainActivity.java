package com.example.kotlin.activity;

import android.os.Bundle;

import com.example.kotlin.AbstractActivity;
import com.example.kotlin.R;

/**
 * Created by chenqi on 2018/7/11 17:50
 * Email:cq_816102@163.com
 * Tips:
 */
public class MainActivity extends AbstractActivity {

    @Override
    public int setContentView() {
        return R.layout.activity_main_layout;
    }

    @Override
    public void bindViews(Bundle saveInstanceState) {

    }

    @Override
    public void initData() {

    }

    /**
     * 设置侧滑不关闭界面
     * @return
     */
    @Override
    protected boolean setSwipeBack() {
        return true;
    }
}
