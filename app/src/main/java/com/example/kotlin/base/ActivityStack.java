package com.example.kotlin.base;

import android.app.Activity;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenqi on 2018/7/11 17:52
 * Email:cq_816102@163.com
 * Tips: Activity栈
 */
public class ActivityStack {
    private static ActivityStack instance;
    private boolean isLock = false;//锁定，主动销毁Activity的时候不执行
    private List<Activity> activityList = new LinkedList<>();

    public ActivityStack() {
    }

    public static ActivityStack getInstance() {
        if (instance == null) {
            synchronized (ActivityStack.class) {
                if (instance == null) {
                    instance = new ActivityStack();
                }
            }
        }
        return instance;
    }

    /**
     * 存放Activity 到List中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }


    /**
     * 判断是否存在 目标Activity
     *
     * @param targetActivity
     * @return
     */
    public boolean hasActivity(Class<Activity> targetActivity) {
        for (Activity activity : activityList) {
            if (activity.getClass() == targetActivity) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从ActivityList 中移除
     *
     * @param activity
     */
    public boolean removeActivity(Activity activity) {
        if (isLock) {
            return activityList.remove(activity);
        }
        return false;
    }


    /**
     * 结束指定的 {@link Activity}
     *
     * @param cls
     */
    public void finish(Class<?> cls) {
        List<Activity> temp = new ArrayList<>(1);
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                temp.add(activity);
            }
        }

        for (Activity activity : temp) {
            activity.finish();
            activityList.remove(activity);
        }
    }

    /**
     * 关闭全部的Activity
     */
    public void finishAllActivity() {
        isLock = true;
        try {
            int size = activityList.size();
            for (int i = 0; i < size; i++) {
                activityList.get(i).finish();
            }
            activityList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isLock = false;
    }


    /**
     * 获取顶部Activity Class
     *
     * @return
     */
    @Nullable
    public Class getTopActivityClass() {
        Activity topActivity = getTopActivity();
        if (topActivity == null) {
            return null;
        }
        return topActivity.getClass();
    }

    /**
     * 获取顶部Activity
     *
     * @return
     */
    @Nullable
    public Activity getTopActivity() {
        int index = activityList.size() - 1;
        if (index < 0 || activityList.isEmpty()) {
            return null;
        }
        return activityList.get(index);
    }
}
