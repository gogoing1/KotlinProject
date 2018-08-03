package com.example.kotlin.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.kotlin.R;

/**
 * Created by chenqi on 2018/8/3 14:47
 * Email:cq_816102@163.com
 * Tips:底部弹出的Dialog
 */
public abstract class BottomDialog extends DialogFragment {

    private DialogInterface onDismissListener;
    private DialogInterface onCancleListener;

    public void setOnDismissListener(DialogInterface onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void setOnCancleListener(DialogInterface onCancleListener) {
        this.onCancleListener = onCancleListener;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params;
        if (window != null) {
            params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
            //window.setWindowAnimations();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(setContentView(), container, false);
    }

    public abstract int setContentView();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public void show(FragmentActivity activity, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }
}
