package com.example.kotlin.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by chenqi on 2018/7/13 11:03
 * Email:cq_816102@163.com
 * Tips:AbstractMvpActivity抽象父类
 */
public abstract class AbstractMvpActivity<T extends BasePresenter> extends AbstractActivity implements BaseView {

    protected T presenter;
    protected Handler mHandler = new LeakHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = newPresenter();
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.onStart();
        }
    }

    /**
     * 初始化Presenter
     * 如果Activity不需要presenter 可以传空
     *
     * @return
     */
    protected abstract T newPresenter();


    /**
     * Toast提示
     *
     * @param msg
     */
    @Override
    public void showToastMessage(String msg) {
        if (TextUtils.isEmpty(msg)) {
            throw new IllegalArgumentException("msg is empty.");
        }
        Toast.makeText(getActivityContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 避免内存泄露的Handler
     */
    protected static class LeakHandler extends Handler {
        WeakReference<AbstractMvpActivity> reference;

        public LeakHandler(AbstractMvpActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference == null) return;
            AbstractMvpActivity activity = reference.get();
            if (activity != null) {
                activity.handMessage(msg);
            }
        }
    }

    /**
     * 子类需实现此方法
     *
     * @param msg
     */
    protected void handMessage(Message msg) {
    }

    @Override
    public AbstractActivity getActivityContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestory();
        }
    }
}
