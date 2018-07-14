package com.example.kotlin;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by chenqi on 2018/7/14 8:57
 * Email:cq_816102@163.com
 * Tips: AbstractMvpFragment抽象父类
 */
public abstract class AbstractMvpFragment<T extends BasePresenter> extends AbstractFragment implements BaseView{

    protected T presenter;

    @Override
    public AbstractActivity getActivityContext() {
        return mContext;
    }

    /**
     * 防止内存泄漏的Handler
     */
    protected static class LeakHandler extends Handler {
        WeakReference<AbstractMvpFragment> reference;

        public LeakHandler(AbstractMvpFragment fragment) {
            reference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference == null) return;
            AbstractMvpFragment fragment = reference.get();
            fragment.handleMessage(msg);
        }
    }

    protected void handleMessage(Message msg) {
    }
}
