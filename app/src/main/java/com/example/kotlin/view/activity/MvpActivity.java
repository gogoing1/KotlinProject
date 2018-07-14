package com.example.kotlin.view.activity;

import android.os.Bundle;

import com.example.kotlin.R;
import com.example.kotlin.base.AbstractMvpActivity;
import com.example.kotlin.presenter.MvpContract;

public class MvpActivity extends AbstractMvpActivity<MvpContract.Presenter> implements MvpContract.View {


    @Override
    public int setContentView() {
        return R.layout.activity_second;
    }

    @Override
    public void bindViews(Bundle saveInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    protected MvpContract.Presenter newPresenter() {
        return null;
    }
}
