package com.example.kotlin.presenter;

import com.example.kotlin.base.BasePresenter;
import com.example.kotlin.base.BaseView;

/**
 * Created by chenqi on 2018/7/14 14:23
 * Email:cq_816102@163.com
 * Tips:
 */
public class MvpContract {

    public static abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }
    }

    public interface View extends BaseView {

    }
}
