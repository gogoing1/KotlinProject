package com.example.kotlin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.kotlin.utils.ScreenUtils;
import com.example.kotlin.utils.SoftInputUtils;

/**
 * Created by chenqi on 2018/7/11 17:50
 * Email:cq_816102@163.com
 * Tips:Activity抽象父类
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected AbstractActivity mContext;
    private boolean swipeBackEnable = false;
    private boolean isFirstEntry = true;
    private boolean swipeGoBack = false;
    private float touchX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
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

    protected abstract void bindViews(Bundle saveInstanceState);

    protected abstract void initData();


    /**
     * 消息提示
     *
     * @param msg
     */
    public void toastMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

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
     * 是否触摸自动关闭软键盘
     * 默认自动关闭
     *
     * @return
     */
    protected boolean isCloseSoftKeyBoardOnTouch() {
        return true;
    }

    /**
     * 处理手势关闭界面
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //处理点击屏幕任意地方关闭当前软键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN && isCloseSoftKeyBoardOnTouch()) {
            View v = getCurrentFocus();
            if (SoftInputUtils.isShouldHideInput(v, ev)) {
                SoftInputUtils.closeSoftInput(mContext, v);
            }
        }

        //处理从屏幕左边往右滑动关闭当前Activity
        if (getSwipeBackEnable()) {
            if (swipeGoBack) {
                if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
                    swipeGoBack = false;
                }
                return true;
            }
            float swipeStart = ScreenUtils.getScreenWidth() / 15;
            float swipeDistance = ScreenUtils.getScreenWidth() / 4;
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    if (ev.getX() < swipeStart)
                        touchX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (touchX > 0) {
                        float dis = Math.abs(ev.getX() - touchX);
                        if (dis > swipeDistance) {
                            swipeGoBack = true;
                            finish();
                            touchX = 0;
                            return true;
                        }
                    } else if (ev.getX() < swipeStart) {
                        touchX = ev.getX();
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    touchX = 0;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    touchX = 0;
                    break;
                default:
                    break;
            }
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
