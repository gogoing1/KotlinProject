package com.example.kotlin;

import android.content.Intent;
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


    /**
     * 处理手势关闭界面
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN && isCloseSoftKeyBoardOnTouch()) {
//            View v = getCurrentFocus();
//        }
        if (getSwipeBackEnable()) {
            finish();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public final void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_old);
    }

    @Override
    public final void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_old, R.anim.slide_left_to_right);
    }


    /**
     * 控制 触摸屏幕是否关闭软键盘
     * 默认开启触摸关闭
     *
     * @return
     */
//    protected boolean isCloseSoftKeyBoardOnTouch() {
//        return true;
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().removeActivity(this);
    }
}
