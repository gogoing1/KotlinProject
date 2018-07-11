package com.example.kotlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by chenqi on 2018/7/11 17:50
 * Email:cq_816102@163.com
 * Tips:Activity抽象父类
 */
public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ActivityStack.getInstance().addActivity(this);
    }

   public abstract int setContentView();

}
