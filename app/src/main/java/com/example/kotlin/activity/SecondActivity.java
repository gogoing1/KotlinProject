package com.example.kotlin.activity;

import android.os.Bundle;

import com.example.kotlin.AbstractActivity;
import com.example.kotlin.R;

public class SecondActivity extends AbstractActivity {


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
}
