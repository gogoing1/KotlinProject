package com.example.kotlin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kotlin.AbstractActivity;
import com.example.kotlin.AbstractMvpActivity;
import com.example.kotlin.BasePresenter;
import com.example.kotlin.R;

/**
 * Created by chenqi on 2018/7/11 17:50
 * Email:cq_816102@163.com
 * Tips:
 */
public class MainActivity extends AbstractMvpActivity {

    @Override
    public int setContentView() {
        return R.layout.activity_main_layout;
    }

    @Override
    public void bindViews(Bundle saveInstanceState) {
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter newPresenter() {
        return null;
    }


    /**
     * 主界面不支持侧滑关闭
     * @return
     */
    @Override
    protected boolean setSwipeBack() {
        return false;
    }
}
