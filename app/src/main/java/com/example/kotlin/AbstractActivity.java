package com.example.kotlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.kotlin.utils.ScreenUtils;

/**
 * Created by chenqi on 2018/7/11 17:50
 * Email:cq_816102@163.com
 * Tips:Activity抽象父类
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected AbstractActivity mContext;
    private boolean swipeBackEnable = false;
    private boolean isFirstEntry = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ActivityStack.getInstance().addActivity(this);
        ScreenUtils.init(this);
        mContext = this;
        bindViews(savedInstanceState);
        setSwipeBackEnable(setSwipeBack());

        if (savedInstanceState != null) {
            restore(savedInstanceState);
        }
    }

    public abstract int setContentView();

    public abstract void bindViews(Bundle saveInstanceState);

    public abstract void initData();


    /**
     * 当前界面获取到焦点的时候 通知加载数据
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFirstEntry) {
            isFirstEntry = false;
            initData();
        }
    }

    /**
     * 页面恢复处理
     *
     * @param savedInstanceState
     */
    protected void restore(Bundle savedInstanceState) {

    }

    /**
     * 启用屏幕侧滑返回，8.0以下默认
     *
     * @return
     */
    protected boolean setSwipeBack() {
        return true;
    }

    protected final void setSwipeBackEnable(boolean swipeBackEnable) {
        this.swipeBackEnable = swipeBackEnable;
    }

    private boolean getSwipeBackEnable() {
        return swipeBackEnable;
    }

}
