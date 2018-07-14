package com.example.kotlin.base;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenqi on 2018/7/13 11:35
 * Email:cq_816102@163.com
 * Tips:Presenter抽象父类
 */
public abstract class BasePresenter<V extends BaseView> {
    private static final String TAG = BasePresenter.class.getSimpleName();

    private V targetView;
    protected V view;// view的代理

    public BasePresenter(V view) {
        this.targetView = view;
        this.view = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new NotNullHandler());
    }

    /**
     * 初始化一些数据
     */
    public abstract void onStart();


    /**
     * 销毁一些数据
     */
    public void onDestory(){
        targetView = null;//释放view
    }


    public class NotNullHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (targetView == null) {//为了防止view 为空，用动态代理在每个方法执行前增加判断
                Log.e(TAG, "什么都没执行.......");
                return null;
            }
            try {
                return method.invoke(targetView, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
