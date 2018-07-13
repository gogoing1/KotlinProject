package com.example.kotlin.utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by chenqi on 2018/7/13 9:18
 * Email:cq_816102@163.com
 * Tips: 软键盘管理工具类
 */
public class SoftInputUtils {

    public SoftInputUtils() {
    }

    /**
     * 弹出软键盘
     *
     * @param context
     * @param focusView
     */
    public static void openSoftInput(Context context, final View focusView) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        focusView.post(new Runnable() {
            @Override
            public void run() {
                focusView.requestFocus();
                imm.showSoftInput(focusView, 2);
            }
        });
    }

    /**
     * 关闭软键盘
     *
     * @param context
     * @param focusView
     */
    public static void closeSoftInput(Context context, final View focusView) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        focusView.clearFocus();
    }

    /**
     * 判断软键盘是否可见
     *
     * @param context
     * @param view
     * @return
     */
    public static boolean isSoftInputActive(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        return imm.isActive();
    }

    /**
     * 判断当前点击区域是否在输入框之内
     *
     * @param v
     * @param e
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent e) {
        if (v != null && v instanceof EditText) {
            int leftTop[] = new int[2];
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int right = left + v.getWidth();
            int bottom = top + v.getHeight();

            //区间函数↓
            return e.getX() < (float) left
                    || e.getY() < (float) top
                    || e.getX() > (float) right
                    || e.getY() > (float) bottom;
        }
        return false;
    }

}
